package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Comment extends RealmObject implements Parcelable {
    @PrimaryKey
    private int id;
    private String body;
    @SerializedName ("parent_comment_id")
    private int parentComment;
    @SerializedName ("child_comments_count")
    private int childCommentCount;
    @SerializedName ("maker")
    private boolean isMaker;
    private User user;
    private int level;
    @SerializedName ("child_comments")
    private RealmList<Comment> childComments;

    public Comment() {
    }

    protected Comment(Parcel in) {
        id = in.readInt();
        body = in.readString();
        parentComment = in.readInt();
        childCommentCount = in.readInt();
        isMaker = in.readByte() != 0;
        user = in.readParcelable(User.class.getClassLoader());
        level = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(body);
        dest.writeInt(parentComment);
        dest.writeInt(childCommentCount);
        dest.writeByte((byte) (isMaker ? 1 : 0));
        dest.writeParcelable(user, flags);
        dest.writeInt(level);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getBody () {
        return body;
    }

    public void setBody (String body) {
        this.body = body;
    }

    public int getParentComment () {
        return parentComment;
    }

    public void setParentComment (int parentComment) {
        this.parentComment = parentComment;
    }

    public int getChildCommentCount () {
        return childCommentCount;
    }

    public void setChildCommentCount (int childCommentCount) {
        this.childCommentCount = childCommentCount;
    }

    public boolean isMaker () {
        return isMaker;
    }

    public void setIsMaker (boolean isMaker) {
        this.isMaker = isMaker;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public int getLevel () {
        return level;
    }

    public void setLevel (int level) {
        this.level = level;
    }

    public RealmList<Comment> getChildComments () {
        return childComments;
    }

    public void setChildComments (RealmList<Comment> childComments) {
        this.childComments = childComments;
    }

    public int getChildCommentsCount () {
        return childComments.size ();
    }

}