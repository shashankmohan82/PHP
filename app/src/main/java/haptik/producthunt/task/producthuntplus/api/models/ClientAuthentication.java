package haptik.producthunt.task.producthuntplus.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ClientAuthentication extends RealmObject implements Parcelable {

    @SerializedName("client_id")
    private String clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("grant_type")
    private String grantType;

    public ClientAuthentication() {
    }

    public ClientAuthentication(String clientId, String clientSecret, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
    }

    protected ClientAuthentication(Parcel in) {
        clientId = in.readString();
        clientSecret = in.readString();
        grantType = in.readString();
    }

    public static final Creator<ClientAuthentication> CREATOR = new Creator<ClientAuthentication>() {
        @Override
        public ClientAuthentication createFromParcel(Parcel in) {
            return new ClientAuthentication(in);
        }

        @Override
        public ClientAuthentication[] newArray(int size) {
            return new ClientAuthentication[size];
        }
    };

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(clientId);
        parcel.writeString(clientSecret);
        parcel.writeString(grantType);
    }
}
