package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ImageUrl extends RealmObject implements Parcelable
{

    @SerializedName("30px")
    @Expose
    private String _30px;
    @SerializedName("32px")
    @Expose
    private String _32px;
    @SerializedName("40px")
    @Expose
    private String _40px;
    @SerializedName("44px")
    @Expose
    private String _44px;
    @SerializedName("48px")
    @Expose
    private String _48px;
    @SerializedName("50px")
    @Expose
    private String _50px;
    @SerializedName("60px")
    @Expose
    private String _60px;
    @SerializedName("64px")
    @Expose
    private String _64px;
    @SerializedName("73px")
    @Expose
    private String _73px;
    @SerializedName("80px")
    @Expose
    private String _80px;
    @SerializedName("88px")
    @Expose
    private String _88px;
    @SerializedName("96px")
    @Expose
    private String _96px;
    @SerializedName("100px")
    @Expose
    private String _100px;
    @SerializedName("110px")
    @Expose
    private String _110px;
    @SerializedName("120px")
    @Expose
    private String _120px;
    @SerializedName("132px")
    @Expose
    private String _132px;
    @SerializedName("146px")
    @Expose
    private String _146px;
    @SerializedName("160px")
    @Expose
    private String _160px;
    @SerializedName("176px")
    @Expose
    private String _176px;
    @SerializedName("220px")
    @Expose
    private String _220px;
    @SerializedName("264px")
    @Expose
    private String _264px;
    @SerializedName("32px@2X")
    @Expose
    private String _32px2X;
    @SerializedName("40px@2X")
    @Expose
    private String _40px2X;
    @SerializedName("44px@2X")
    @Expose
    private String _44px2X;
    @SerializedName("88px@2X")
    @Expose
    private String _88px2X;
    @SerializedName("32px@3X")
    @Expose
    private String _32px3X;
    @SerializedName("40px@3X")
    @Expose
    private String _40px3X;
    @SerializedName("44px@3X")
    @Expose
    private String _44px3X;
    @SerializedName("88px@3X")
    @Expose
    private String _88px3X;
    @SerializedName("original")
    @Expose
    private String original;

    public ImageUrl() {
    }

    protected ImageUrl(Parcel in) {
        _30px = in.readString();
        _32px = in.readString();
        _40px = in.readString();
        _44px = in.readString();
        _48px = in.readString();
        _50px = in.readString();
        _60px = in.readString();
        _64px = in.readString();
        _73px = in.readString();
        _80px = in.readString();
        _88px = in.readString();
        _96px = in.readString();
        _100px = in.readString();
        _110px = in.readString();
        _120px = in.readString();
        _132px = in.readString();
        _146px = in.readString();
        _160px = in.readString();
        _176px = in.readString();
        _220px = in.readString();
        _264px = in.readString();
        _32px2X = in.readString();
        _40px2X = in.readString();
        _44px2X = in.readString();
        _88px2X = in.readString();
        _32px3X = in.readString();
        _40px3X = in.readString();
        _44px3X = in.readString();
        _88px3X = in.readString();
        original = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_30px);
        dest.writeString(_32px);
        dest.writeString(_40px);
        dest.writeString(_44px);
        dest.writeString(_48px);
        dest.writeString(_50px);
        dest.writeString(_60px);
        dest.writeString(_64px);
        dest.writeString(_73px);
        dest.writeString(_80px);
        dest.writeString(_88px);
        dest.writeString(_96px);
        dest.writeString(_100px);
        dest.writeString(_110px);
        dest.writeString(_120px);
        dest.writeString(_132px);
        dest.writeString(_146px);
        dest.writeString(_160px);
        dest.writeString(_176px);
        dest.writeString(_220px);
        dest.writeString(_264px);
        dest.writeString(_32px2X);
        dest.writeString(_40px2X);
        dest.writeString(_44px2X);
        dest.writeString(_88px2X);
        dest.writeString(_32px3X);
        dest.writeString(_40px3X);
        dest.writeString(_44px3X);
        dest.writeString(_88px3X);
        dest.writeString(original);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageUrl> CREATOR = new Creator<ImageUrl>() {
        @Override
        public ImageUrl createFromParcel(Parcel in) {
            return new ImageUrl(in);
        }

        @Override
        public ImageUrl[] newArray(int size) {
            return new ImageUrl[size];
        }
    };

    public String get30px() {
        return _30px;
    }

    public void set30px(String _30px) {
        this._30px = _30px;
    }

    public String get32px() {
        return _32px;
    }

    public void set32px(String _32px) {
        this._32px = _32px;
    }

    public String get40px() {
        return _40px;
    }

    public void set40px(String _40px) {
        this._40px = _40px;
    }

    public String get44px() {
        return _44px;
    }

    public void set44px(String _44px) {
        this._44px = _44px;
    }

    public String get48px() {
        return _48px;
    }

    public void set48px(String _48px) {
        this._48px = _48px;
    }

    public String get50px() {
        return _50px;
    }

    public void set50px(String _50px) {
        this._50px = _50px;
    }

    public String get60px() {
        return _60px;
    }

    public void set60px(String _60px) {
        this._60px = _60px;
    }

    public String get64px() {
        return _64px;
    }

    public void set64px(String _64px) {
        this._64px = _64px;
    }

    public String get73px() {
        return _73px;
    }

    public void set73px(String _73px) {
        this._73px = _73px;
    }

    public String get80px() {
        return _80px;
    }

    public void set80px(String _80px) {
        this._80px = _80px;
    }

    public String get88px() {
        return _88px;
    }

    public void set88px(String _88px) {
        this._88px = _88px;
    }

    public String get96px() {
        return _96px;
    }

    public void set96px(String _96px) {
        this._96px = _96px;
    }

    public String get100px() {
        return _100px;
    }

    public void set100px(String _100px) {
        this._100px = _100px;
    }

    public String get110px() {
        return _110px;
    }

    public void set110px(String _110px) {
        this._110px = _110px;
    }

    public String get120px() {
        return _120px;
    }

    public void set120px(String _120px) {
        this._120px = _120px;
    }

    public String get132px() {
        return _132px;
    }

    public void set132px(String _132px) {
        this._132px = _132px;
    }

    public String get146px() {
        return _146px;
    }

    public void set146px(String _146px) {
        this._146px = _146px;
    }

    public String get160px() {
        return _160px;
    }

    public void set160px(String _160px) {
        this._160px = _160px;
    }

    public String get176px() {
        return _176px;
    }

    public void set176px(String _176px) {
        this._176px = _176px;
    }

    public String get220px() {
        return _220px;
    }

    public void set220px(String _220px) {
        this._220px = _220px;
    }

    public String get264px() {
        return _264px;
    }

    public void set264px(String _264px) {
        this._264px = _264px;
    }

    public String get32px2X() {
        return _32px2X;
    }

    public void set32px2X(String _32px2X) {
        this._32px2X = _32px2X;
    }

    public String get40px2X() {
        return _40px2X;
    }

    public void set40px2X(String _40px2X) {
        this._40px2X = _40px2X;
    }

    public String get44px2X() {
        return _44px2X;
    }

    public void set44px2X(String _44px2X) {
        this._44px2X = _44px2X;
    }

    public String get88px2X() {
        return _88px2X;
    }

    public void set88px2X(String _88px2X) {
        this._88px2X = _88px2X;
    }

    public String get32px3X() {
        return _32px3X;
    }

    public void set32px3X(String _32px3X) {
        this._32px3X = _32px3X;
    }

    public String get40px3X() {
        return _40px3X;
    }

    public void set40px3X(String _40px3X) {
        this._40px3X = _40px3X;
    }

    public String get44px3X() {
        return _44px3X;
    }

    public void set44px3X(String _44px3X) {
        this._44px3X = _44px3X;
    }

    public String get88px3X() {
        return _88px3X;
    }

    public void set88px3X(String _88px3X) {
        this._88px3X = _88px3X;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
