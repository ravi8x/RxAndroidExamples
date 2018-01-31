package info.androidhive.rxandroidexamples.networking.retrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ravi on 31/01/18.
 */

public class Contact {
    String name;

    @SerializedName("image")
    String profileImage;

    String phone;

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getPhone() {
        return phone;
    }
}
