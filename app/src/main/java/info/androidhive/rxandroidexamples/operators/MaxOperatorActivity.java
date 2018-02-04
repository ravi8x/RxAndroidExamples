package info.androidhive.rxandroidexamples.operators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.androidhive.rxandroidexamples.R;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import rx.Subscriber;
import rx.observables.MathObservable;

public class MaxOperatorActivity extends AppCompatActivity {

    private static final String TAG = CountOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_operator);

        Observable<Integer> observable = Observable.range(120, 34);

        // TODO - check why max method is not taking proper params
        // MathObservable.max(observable);
    }

    private Observable<Integer> getNumbersObservable(){
        return Observable.range(1, 6);
    }
}
