package info.androidhive.rxandroidexamples.operators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.rxandroidexamples.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ZipOperatorActivity extends AppCompatActivity {

    private static final String TAG = ZipOperatorActivity.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_operator);

        disposable.add(Observable.zip(
                getAddressObservable(), getPhotosObservable(), new BiFunction<Address, Photo, User>() {
                    @Override
                    public User apply(Address address, Photo photo) throws Exception {
                        User user = new User();
                        user.setAddress(address);
                        user.setPhoto(photo);
                        return user;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext- Address: " + user.getAddress().getAddress() + ", Photo: " +
                                user.getPhoto().getUrl() + ", " + user.getPhoto().getWidth() + "x" + user.getPhoto().getHeight());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All users are emitted!");
                    }
                }));
        ;

    }

    private Observable<Photo> getPhotosObservable() {
        final List<Photo> photos = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Photo photo = new Photo();
            photo.setUrl("https://example.com/photo/" + i + ".jpg");
            photo.setWidth(100);
            photo.setHeight(150);

            photos.add(photo);
        }

        return Observable.create(new ObservableOnSubscribe<Photo>() {
            @Override
            public void subscribe(ObservableEmitter<Photo> emitter) throws Exception {
                for (Photo photo : photos) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(photo);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    private Observable<Address> getAddressObservable() {

        final String[] addreStrns = new String[]{
                "1600 Amphitheatre Parkway, Mountain View, CA 94043",
                "2300 Traverwood Dr. Ann Arbor, MI 48105",
                "500 W 2nd St Suite 2900 Austin, TX 78701",
                "355 Main Street Cambridge, MA 02142"
        };

        final List<Address> addresses = new ArrayList<>();

        for (String a : addreStrns) {
            Address address = new Address();
            address.setAddress(a);

            addresses.add(address);
        }

        return Observable.create(new ObservableOnSubscribe<Address>() {
            @Override
            public void subscribe(ObservableEmitter<Address> emitter) throws Exception {
                for (Address address : addresses) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(address);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    class User {
        Address address;
        Photo photo;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }
    }

    class Address {
        String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    class Photo {
        String url;
        int width;
        int height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
