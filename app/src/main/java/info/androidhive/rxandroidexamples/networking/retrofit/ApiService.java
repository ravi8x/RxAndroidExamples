package info.androidhive.rxandroidexamples.networking.retrofit;

import java.util.List;

import info.androidhive.rxandroidexamples.networking.retrofit.model.Contact;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ravi on 31/01/18.
 */

public interface ApiService {

    @GET("contacts.php")
    Single<List<Contact>> getContacts(@Query("source") String source, @Query("search") String query);
}
