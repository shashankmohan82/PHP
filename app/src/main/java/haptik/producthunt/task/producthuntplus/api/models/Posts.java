package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

;


public class Posts extends RealmObject implements Parcelable{

    @SerializedName("posts")
    @Expose
    private RealmList<Post> posts;

    public Posts() {
    }

    protected Posts(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    public RealmList<Post> getPosts() {
        return posts;
    }

    public void setPosts(RealmList<Post> posts) {
        this.posts = posts;
    }

}


