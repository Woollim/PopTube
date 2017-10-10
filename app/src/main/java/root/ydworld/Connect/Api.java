package root.ydworld.Connect;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by root1 on 2017. 10. 9..
 */

public interface Api {

    @GET("search")
    @Streaming
    Call<ResponseBody> downloadVideo(@Query("url") String url, @Query("type") String type);

    @GET("title")
    Call<String> getTitle(@Query("url") String url);

}
