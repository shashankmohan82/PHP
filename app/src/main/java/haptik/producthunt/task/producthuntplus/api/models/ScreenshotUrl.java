package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ScreenshotUrl  extends RealmObject implements Parcelable {

    @SerializedName("300px")
    @Expose
    private String _300px;
    @SerializedName("850px")
    @Expose
    private String _850px;

    public ScreenshotUrl() {
    }

    protected ScreenshotUrl(Parcel in) {
        _300px = in.readString();
        _850px = in.readString();
    }

    public static final Creator<ScreenshotUrl> CREATOR = new Creator<ScreenshotUrl>() {
        @Override
        public ScreenshotUrl createFromParcel(Parcel in) {
            return new ScreenshotUrl(in);
        }

        @Override
        public ScreenshotUrl[] newArray(int size) {
            return new ScreenshotUrl[size];
        }
    };

    public String get300px() {
        return _300px;
    }

    public void set300px(String _300px) {
        this._300px = _300px;
    }

    public String get850px() {
        return _850px;
    }

    public void set850px(String _850px) {
        this._850px = _850px;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_300px);
        parcel.writeString(_850px);
    }
}
