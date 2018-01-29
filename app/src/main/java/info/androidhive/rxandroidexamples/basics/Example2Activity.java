package info.androidhive.rxandroidexamples.basics;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.rxandroidexamples.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Example2Activity extends AppCompatActivity {

    /**
     * With subscribe and un-subscribe
     */
    private static final String TAG = Example2Activity.class.getSimpleName();

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);

        Observable<String> animalsObservable = getAnimalsObservable();

        DisposableObserver<String> animalsObserver = getAnimalsObserver();

        disposables.add(
                animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(animalsObserver));
    }

    private DisposableObserver<String> getAnimalsObserver() {
        return new DisposableObserver<String>() {

            @Override
            public void onNext(String s) {
                Log.e(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<String> getAnimalsObservable() {
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // don't send events once the activity is destroyed
        disposables.clear();
    }
}
