package haptik.producthunt.task.producthuntplus.network;


import haptik.producthunt.task.producthuntplus.api.models.AccessToken;
import haptik.producthunt.task.producthuntplus.api.models.ClientAuthentication;
import haptik.producthunt.task.producthuntplus.api.models.Comments;
import haptik.producthunt.task.producthuntplus.api.models.Posts;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by sHIVAM on 6/14/2017.
 */
public interface ProductHuntAPIService {
    @POST("oauth/token")
    Observable<AccessToken> getAccessToken(@Body ClientAuthentication auth);

    @GET("posts")
    Observable<Posts> getPosts();

    @GET ("posts")
    Observable<Posts> getPostsByDate(@Query("day") String date);

    @GET ("posts")
    Observable<Posts> getPostsDaysAgo(@Query("days_ago") int daysAgo);

    @GET ("posts/{product-id}/comments")
    Observable<Comments> getComments(@Path("product-id") int productId);
}
