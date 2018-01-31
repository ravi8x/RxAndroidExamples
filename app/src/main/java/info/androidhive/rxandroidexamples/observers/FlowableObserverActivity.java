package info.androidhive.rxandroidexamples.observers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.androidhive.rxandroidexamples.R;
import info.androidhive.rxandroidexamples.observers.model.Note;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FlowableObserverActivity extends AppCompatActivity {

    private static final String TAG = FlowableObserverActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable_observer);

        Flowable<Note> notesObservalbe = getNotesObservable();

        Observer<Note> notesObserver = getNotesObserver();

        // TODO - yet to complete this example
    }

    private Observer<Note> getNotesObserver() {
        return new Observer<Note>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Note note) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Flowable<Note> getNotesObservable() {
        return Flowable.create(new FlowableOnSubscribe<Note>() {
            @Override
            public void subscribe(FlowableEmitter<Note> emitter) throws Exception {

            }
        }, BackpressureStrategy.BUFFER);
    }
}
