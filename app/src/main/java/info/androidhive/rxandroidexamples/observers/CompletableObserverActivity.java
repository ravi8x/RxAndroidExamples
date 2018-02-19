package info.androidhive.rxandroidexamples.observers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.androidhive.rxandroidexamples.R;
import info.androidhive.rxandroidexamples.observers.model.Note;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompletableObserverActivity extends AppCompatActivity {

    private static final String TAG = CompletableObserverActivity.class.getSimpleName();
    private Disposable disposable;

    /**
     * Completable won't emit any item, instead it returns
     * Success or failure state
     * Consider an example of making a PUT request to server to update
     * something where you are not expecting any response but the
     * success status
     * -
     * Completable : CompletableObserver
     */
    // TODO - link to Retrofit  tutorial
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completable_observer);

        Note note = new Note(1, "Home work!");

        Completable completableObservable = updateNote(note);

        CompletableObserver completableObserver = completableObserver();

        completableObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(completableObserver);
    }


    /**
     * Assume this making PUT request to server to update the Note
     */
    private Completable updateNote(Note note) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    Thread.sleep(1000);
                    emitter.onComplete();
                }
            }
        });
    }

    private CompletableObserver completableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Note updated successfully!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}