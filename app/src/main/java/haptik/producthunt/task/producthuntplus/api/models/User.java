package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Parcelable{

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("twitter_username")
    @Expose
    private String twitterUsername;
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @SerializedName("profile_url")
    @Expose
    private String profileUrl;
    @SerializedName("image_url")
    @Expose
    private ImageUrl imageUrl;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        createdAt = in.readString();
        name = in.readString();
        username = in.readString();
        headline = in.readString();
        twitterUsername = in.readString();
        websiteUrl = in.readString();
        profileUrl = in.readString();
        imageUrl = in.readParcelable(ImageUrl.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(createdAt);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(headline);
        parcel.writeString(twitterUsername);
        parcel.writeString(websiteUrl);
        parcel.writeString(profileUrl);
        parcel.writeParcelable(imageUrl, i);
    }
}
