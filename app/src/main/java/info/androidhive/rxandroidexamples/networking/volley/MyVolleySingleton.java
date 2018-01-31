package info.androidhive.rxandroidexamples.networking.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ravi on 30/01/18.
 */

public class MyVolleySingleton {
    private static RequestQueue mRequestQueue;

    private MyVolleySingleton() {
        // no instances
    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }
}
