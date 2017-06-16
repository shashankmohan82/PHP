package task.application.com.moviefinder.util;

import com.androidtmdbwrapper.Tmdb;

import okhttp3.OkHttpClient;

/**
 * Created by sHIVAM on 2/20/2017.
 */

public class TmdbApi extends Tmdb {

    private static final OkHttpClient client = new OkHttpClient();
    private static task.application.com.moviefinder.util.TmdbApi instance;
    public static final String API_KEY = "api_key";
    private final String apiKey;

    private TmdbApi(String apiKey) {
        super(apiKey);
        this.apiKey = apiKey;
    }

    String getApiKey() {
        return apiKey;
    }

    @Override
    protected void configOkHttpClient(OkHttpClient.Builder builder) {
        builder.addInterceptor(new task.application.com.moviefinder.util.CustomInterceptor(this));
    }

    @Override
    protected synchronized OkHttpClient okHttpClient() {
        if (getOkHttpClient() == null) {
            OkHttpClient.Builder builder = client.newBuilder();
            setOkHttpClient(builder.build());
        }
        return getOkHttpClient();
    }

    public static task.application.com.moviefinder.util.TmdbApi getApiClient(String apiKey) {
        if (instance == null)
            instance = new task.application.com.moviefinder.util.TmdbApi(apiKey);
        return instance;
    }
}
