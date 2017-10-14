package root.ydworld.Connect;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root1 on 2017. 10. 9..
 */

public abstract class CallResponce<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.e("xxx", response.headers().get("Content-Disposition"));

    }

    private Context activity;

    public CallResponce(Context activity) {
        this.activity = activity;
    }

    //abstract public void callBack(int code, T body, @Nullable String fileName);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(activity, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
    }
}
