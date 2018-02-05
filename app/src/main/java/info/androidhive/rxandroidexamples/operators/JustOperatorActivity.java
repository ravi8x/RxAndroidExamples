package info.androidhive.rxandroidexamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.androidhive.rxandroidexamples.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class JustOperatorActivity extends AppCompatActivity {

    private static final String TAG = JustOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_operator);

        // TODO - write limitations of just
        // it takes only 1 - 9 params. Try adding 10 at the end,
        // you will get syntax error
        // take arguments as params, whereas from takes iterable (array)
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "just onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "just onComplete");
                    }
                });
    }
}
