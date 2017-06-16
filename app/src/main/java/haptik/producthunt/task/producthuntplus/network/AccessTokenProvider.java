package haptik.producthunt.task.producthuntplus.network;

import android.util.Log;


import java.io.IOException;

import haptik.producthunt.task.producthuntplus.api.APIConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sHIVAM on 6/14/2017.
 */
public class AccessTokenProvider implements Interceptor {

    private boolean isNetworkActive;

    public AccessTokenProvider() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return handleIntercept(chain, APIConfig.ACCESS_TOKEN);
    }

    private Response handleIntercept(Chain chain, String accessToken) throws IOException {
        Request request = chain.request();
        if(!APIConfig.API_BASE_URL.equals(request.url().host())) {
            return chain.proceed(request);
        }
        Headers.Builder headers = request.headers().newBuilder();
        headers.add("Accept", "application/json");
        headers.add("Authorization", "Bearer "+accessToken);
        Log.d("test", headers.get("Accept"));
        Request.Builder builder = request.newBuilder();
        builder.headers(headers.build());
        return chain.proceed(builder.build());
    }

}

class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No network available, please check your WiFi or Data connection";
    }
}
