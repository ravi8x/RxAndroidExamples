package info.androidhive.rxandroidexamples.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.androidhive.rxandroidexamples.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BufferOperatorActivity extends AppCompatActivity {

    private static final String TAG = BufferOperatorActivity.class.getSimpleName();

    @BindView(R.id.tap_result)
    TextView txtTapResult;

    @BindView(R.id.tap_result_max_count)
    TextView txtTapResultMax;

    @BindView(R.id.layout_tap_area)
    Button btnTapArea;

    private Disposable disposable;
    private Unbinder unbinder;
    private int maxTaps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_operator);
        unbinder = ButterKnife.bind(this);

        RxView.clicks(btnTapArea)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object o) throws Exception {
                        return 1;
                    }
                })
                .buffer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.e(TAG, "onNext: " + integers.size() + " taps received!");
                        if (integers.size() > 0) {
                            maxTaps = integers.size() > maxTaps ? integers.size() : maxTaps;
                            txtTapResult.setText(String.format("Received %d taps in 3 secs", integers.size()));
                            txtTapResultMax.setText(String.format("Maximum of %d taps received in this session", maxTaps));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });


        /**
         * Example of buffer emitting 3 items at a time
         * */
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4,
                5, 6, 7, 8, 9);

        integerObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "onNext");
                        for (Integer integer : integers) {
                            Log.d(TAG, "Item: " + integer);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All items emitted!");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposable.dispose();
    }
}
