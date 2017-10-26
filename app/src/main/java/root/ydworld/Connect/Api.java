package root.ydworld.Connect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by root1 on 2017. 10. 9..
 */

public interface Api {

    @GET
    Call<String> getDownloadPath(@Url String url);

}
