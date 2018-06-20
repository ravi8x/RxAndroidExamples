package info.androidhive.rxandroidexamples.rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jakewharton.rxbinding2.widget.RxAdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.rxandroidexamples.R;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SpinnerRxBindingActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();

    @BindView(R.id.spinner) Spinner spinner;
    String[] planets = { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_rx_binding);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        RxAdapterView.itemSelections(spinner)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(planet -> {
                    Log.e(TAG,"planet: "+planet);
                });
    }
}
