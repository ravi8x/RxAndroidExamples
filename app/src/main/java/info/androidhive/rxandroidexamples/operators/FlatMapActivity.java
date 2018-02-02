package info.androidhive.rxandroidexamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.androidhive.rxandroidexamples.R;

public class FlatMapActivity extends AppCompatActivity {

    /**
     * https://github.com/ReactiveX/RxJava/issues/442
     * If they are dependent on each other, then map/flatMap off the first into the second.
            geocodeAddressRequest.flatMap( address -> {
            return api.createRide(address)
        })
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map);
    }
}
