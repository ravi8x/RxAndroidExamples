package info.androidhive.rxandroidexamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.androidhive.rxandroidexamples.R;
import rx.Observable;
import rx.Subscriber;
import rx.observables.MathObservable;

public class AverageOperatorActivity extends AppCompatActivity {

    private static final String TAG = AverageOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_operator);

        Observable<Integer> numbersObservable = Observable.just(1, 2, 3, 4, 5);
        MathObservable.averageInteger(numbersObservable)
                .subscribe(new rx.Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "Average of 1,2,3,4,5 = " + integer);
                    }
                });

        Observable<Float> floatObservable = Observable.just(10.5f, 11.5f, 14.5f);
        MathObservable.averageFloat(floatObservable)
                .subscribe(new Subscriber<Float>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Float aFloat) {
                        Log.e(TAG, "Average of 10.5f, 11.5f, 14.5f= " + aFloat);
                    }
                });

        Observable<Double> doubleObservable = Observable.just(13.5D, 45.3D, 33.6D);
        MathObservable.averageDouble(doubleObservable)
                .subscribe(new Subscriber<Double>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Double aDouble) {
                        Log.e(TAG, "Average of 13.5D, 45.3D, 33.6D= " + aDouble);
                    }
                });
    }
}
