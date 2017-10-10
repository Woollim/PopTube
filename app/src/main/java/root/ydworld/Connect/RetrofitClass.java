package root.ydworld.Connect;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root1 on 2017. 10. 9..
 */

public class RetrofitClass {
    private static final RetrofitClass ourInstance = new RetrofitClass();

    private Retrofit retrofit;
    
    public Api api;
    
    private String url = "http://10.156.145.113:4875";
    
    public static RetrofitClass getInstance() {
        return ourInstance;
    }

    private RetrofitClass() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(url)
                .build();
        
        api = retrofit.create(Api.class);
    }
}
