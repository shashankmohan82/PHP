package haptik.producthunt.task.producthuntplus.api;

import haptik.producthunt.task.producthuntplus.network.AccessTokenProvider;
import haptik.producthunt.task.producthuntplus.network.ProductHuntAPIService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductHuntAPI {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

    protected OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient client) {
        okHttpClient = client;
    }

    private Retrofit.Builder retrofitBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(APIConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient.Builder clientBuilder = okHttpClient().newBuilder();
        configOkHttpClient(clientBuilder);
        return builder.client(clientBuilder.build());
    }

    protected synchronized OkHttpClient okHttpClient() {
        if(okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    private synchronized Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = retrofitBuilder().build();
        }
        return retrofit;
    }

    protected void configOkHttpClient(OkHttpClient.Builder builder) {
        builder.addInterceptor(new AccessTokenProvider());
    }

    public ProductHuntAPIService apiService(){
        return getRetrofit().create(ProductHuntAPIService.class);
    }
}
