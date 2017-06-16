package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Comments extends RealmObject implements Parcelable{

    @PrimaryKey
    private int postID;
    RealmList<Comment> comments;

    public Comments() {
    }

    protected Comments(Parcel in) {
        postID = in.readInt();
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public RealmList<Comment> getComments() {
        return comments;
    }

    public void setComments(RealmList<Comment> comments) {
        this.comments = comments;
    }

    public int getCount() {
        return comments.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(postID);
    }
}
