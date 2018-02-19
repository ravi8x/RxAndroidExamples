package info.androidhive.rxandroidexamples.observers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.androidhive.rxandroidexamples.R;
import info.androidhive.rxandroidexamples.observers.model.Note;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MaybeObserverActivity extends AppCompatActivity {

    private static final String TAG = MaybeObserverActivity.class.getSimpleName();
    private Disposable disposable;

    /**
     * Consider an example getting a note from db using ID
     * There is possibility of not finding the note by ID in the db
     * In this situation, MayBe can be used
     * -
     * Maybe : MaybeObserver
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maybe_observer);

        Maybe<Note> noteObservable = getNoteObservable();

        MaybeObserver<Note> noteObserver = getNoteObserver();

        noteObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(noteObserver);
    }

    private MaybeObserver<Note> getNoteObserver() {
        return new MaybeObserver<Note>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(Note note) {
                Log.d(TAG, "onSuccess: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };
    }

    /**
     * Emits optional data (0 or 1 emission)
     * But for now it emits 1 Note always
     */
    private Maybe<Note> getNoteObservable() {
        return Maybe.create(new MaybeOnSubscribe<Note>() {
            @Override
            public void subscribe(MaybeEmitter<Note> emitter) throws Exception {
                Note note = new Note(1, "Call brother!");
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(note);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}