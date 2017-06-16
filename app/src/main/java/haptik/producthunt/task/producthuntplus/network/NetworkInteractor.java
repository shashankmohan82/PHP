package haptik.producthunt.task.producthuntplus.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import io.reactivex.Completable;
import io.reactivex.annotations.NonNull;

/**
 * Created by sHIVAM on 6/15/2017.
 */
public class NetworkInteractor {
    private ConnectivityManager connectivityManager;

    public NetworkInteractor(@NonNull ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public boolean hasInternetConnectivity() {
        NetworkInfo activeNet = connectivityManager.getActiveNetworkInfo();
        return activeNet!=null && activeNet.isConnectedOrConnecting();
    }

    @NonNull
    public Completable hasInternetConnection() {
        if(hasInternetConnectivity())
            return Completable.complete();
        return Completable.error(new Throwable("Network Connection not available!"));
    }
}
