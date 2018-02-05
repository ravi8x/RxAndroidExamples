package info.androidhive.rxandroidexamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import info.androidhive.rxandroidexamples.R;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

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
                            txtTapResultMax.setText(String.format("%d maximum taps in 3 secs", maxTaps));
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposable.dispose();
    }
}
