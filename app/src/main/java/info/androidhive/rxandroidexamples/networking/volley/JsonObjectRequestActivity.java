package info.androidhive.rxandroidexamples.networking.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import info.androidhive.rxandroidexamples.R;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class JsonObjectRequestActivity extends AppCompatActivity {

    private static final String TAG = JsonObjectRequestActivity.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();

    private static final String URL = "https://api.androidhive.info/volley/person_object.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_object_request);

        MyVolleySingleton.init(this);


        SingleObserver<JSONObject> d = new SingleObserver<JSONObject>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.e(TAG, "onNext: " + jsonObject.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        };

        getFeedObservableSingle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d);
    }

    public Single<JSONObject> getFeedObservableSingle() {
        try {
            return Single.just(getFeedObject());
        } catch (InterruptedException e) {
            return Single.error(e);
        } catch (ExecutionException e) {
            return Single.error(e);
        }
    }

    public JSONObject getFeedObject() throws ExecutionException, InterruptedException {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, URL, future, future);
        MyVolleySingleton.getRequestQueue().add(req);
        return future.get();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
