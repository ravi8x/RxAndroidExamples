package info.androidhive.rxandroidexamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import info.androidhive.rxandroidexamples.R;
import info.androidhive.rxandroidexamples.operators.model.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ConcatOperatorActivity extends AppCompatActivity {

    // executes sequentially and make sure all are emitted

    private static final String TAG = ConcatOperatorActivity.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat_operator);

        disposable.add(Observable.concat(getMaleUsersObservale(), getFemaleUsersObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<User>>() {
                    @Override
                    public void onNext(List<User> users) {
                        for (User user : users) {
                            Log.d(TAG, user.getName() + ", " + user.getGender());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private Observable<List<User>> getMaleUsersObservale() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {

                // add comment
                Thread.sleep(3000);

                String[] names = new String[]{"Mark", "John", "Trump", "Obama"};

                List<User> users = new ArrayList<>();
                for (String name : names) {
                    User user = new User();
                    user.setName(name);
                    user.setGender("male");

                    users.add(user);
                }

                return users;
            }
        });
    }

    private Observable<List<User>> getFemaleUsersObservable() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {

                String[] names = new String[]{"Lucy", "Scarlett", "April"};

                List<User> users = new ArrayList<>();
                for (String name : names) {
                    User user = new User();
                    user.setName(name);
                    user.setGender("female");

                    users.add(user);
                }

                return users;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
