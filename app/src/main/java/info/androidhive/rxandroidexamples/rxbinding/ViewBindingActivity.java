package info.androidhive.rxandroidexamples.rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.androidhive.rxandroidexamples.R;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ViewBindingActivity extends AppCompatActivity {

    private static final String TAG = ViewBindingActivity.class.getSimpleName();

    private Unbinder unbinder;
    private CompositeDisposable disposable = new CompositeDisposable();

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_binding);
        unbinder = ButterKnife.bind(this);

        RxView.clicks(button).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG, "onClick:");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
