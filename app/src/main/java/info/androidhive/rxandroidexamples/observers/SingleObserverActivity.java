package info.androidhive.rxandroidexamples.observers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.androidhive.rxandroidexamples.R;
import info.androidhive.rxandroidexamples.observers.model.Note;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SingleObserverActivity extends AppCompatActivity {

    private static final String TAG = SingleObserverActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_observer);

        Single<Note> noteObservable = getNoteObservable();

        SingleObserver<Note> singleObserver = getSingleObserver();

        noteObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(singleObserver);

    }

    private SingleObserver<Note> getSingleObserver() {
        return new SingleObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Note note) {
                Log.e(TAG, "onSuccess: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {

            }
        };
    }

    private Single<Note> getNoteObservable() {
        return Single.create(new SingleOnSubscribe<Note>() {
            @Override
            public void subscribe(SingleEmitter<Note> emitter) throws Exception {
                Thread.sleep(2000);

                Note note = new Note(1, "Buy milk!");
                emitter.onSuccess(note);
            }
        });
    }
}
