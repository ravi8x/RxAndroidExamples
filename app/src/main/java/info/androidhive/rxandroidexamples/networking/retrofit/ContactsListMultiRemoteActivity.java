package info.androidhive.rxandroidexamples.networking.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.androidhive.rxandroidexamples.R;
import info.androidhive.rxandroidexamples.networking.retrofit.adapter.ContactsAdapter;
import info.androidhive.rxandroidexamples.networking.retrofit.model.Contact;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactsListMultiRemoteActivity extends AppCompatActivity implements ContactsAdapter.ContactsAdapterListener {

    private static final String TAG = ContactsListMultiRemoteActivity.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiService apiService;
    private ContactsAdapter mAdapter;
    private List<Contact> contactsList = new ArrayList<>();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list_multi_remote);
        unbinder = ButterKnife.bind(this);

        mAdapter = new ContactsAdapter(this, contactsList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        apiService = ApiClient.getClient().create(ApiService.class);


        // source: `gmail` or `linkedin`
        fetchContactsFromMultipleApiSources();
    }

    private void fetchContactsFromMultipleApiSources() {
        Single.zip(getGmailContacts(), getLinkedInContacts(),
                new BiFunction<List<Contact>, List<Contact>, List<Contact>>() {
                    @Override
                    public List<Contact> apply(List<Contact> gmailContacts, List<Contact> linkedInContacts) throws Exception {
                        return compareAndMergeContacts(gmailContacts, linkedInContacts);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {
                    @Override
                    public void onSuccess(List<Contact> contacts) {
                        Log.d(TAG, "onSuccess: " + contacts.size());
                        contactsList.clear();
                        contactsList.addAll(contacts);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    // merging two array lists
    private List<Contact> compareAndMergeContacts(List<Contact> gmailContacts, List<Contact> linkedInContacts) {
        List<Contact> contacts = new ArrayList<>();
        contacts.addAll(gmailContacts);
        for (Contact contact : linkedInContacts) {
            if (!contacts.contains(contact)) {
                contacts.add(contact);
            }
        }
        return contacts;
    }

    public Single<List<Contact>> getGmailContacts() {
        return apiService.getContacts("gmail", null);
    }

    public Single<List<Contact>> getLinkedInContacts() {
        return apiService.getContacts("linkedin", null);
    }


    @Override
    protected void onDestroy() {
        disposable.clear();
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onContactSelected(Contact contact) {

    }
}
