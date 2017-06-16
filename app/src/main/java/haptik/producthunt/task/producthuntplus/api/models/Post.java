package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Post extends RealmObject implements Parcelable{

    @SerializedName("category_id")
    @Expose
    private int categoryId;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("comments_count")
    @Expose
    private int commentsCount;
    @SerializedName("discussion_url")
    @Expose
    private String discussionUrl;
    @SerializedName("redirect_url")
    @Expose
    private String redirectUrl;
    @SerializedName("screenshot_url")
    @Expose
    private ScreenshotUrl screenshotUrl;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("votes_count")
    @Expose
    private int votesCount;

    public Post() {
    }

    protected Post(Parcel in) {
        categoryId = in.readInt();
        day = in.readString();
        id = in.readInt();
        name = in.readString();
        tagline = in.readString();
        commentsCount = in.readInt();
        discussionUrl = in.readString();
        redirectUrl = in.readString();
        screenshotUrl = in.readParcelable(ScreenshotUrl.class.getClassLoader());
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        votesCount = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getDiscussionUrl() {
        return discussionUrl;
    }

    public void setDiscussionUrl(String discussionUrl) {
        this.discussionUrl = discussionUrl;
    }





    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public ScreenshotUrl getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(ScreenshotUrl screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }



    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(categoryId);
        parcel.writeString(day);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(tagline);
        parcel.writeInt(commentsCount);
        parcel.writeString(discussionUrl);
        parcel.writeString(redirectUrl);
        parcel.writeParcelable(screenshotUrl, i);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeInt(votesCount);
    }
}
