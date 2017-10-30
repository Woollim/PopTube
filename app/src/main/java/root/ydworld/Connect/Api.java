package root.ydworld.Connect;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by root1 on 2017. 10. 9..
 */

public interface Api {

    @POST("/sendError")
    @FormUrlEncoded
    Call<Void> sendError(@Field("id") String id, @Field("error") String error);

    @POST("/link")
    @FormUrlEncoded
    Call<Void> sendLink(@Field("id") String id, @Field("link") String link);

    @POST("/set")
    Call<JsonObject> getId();

}
