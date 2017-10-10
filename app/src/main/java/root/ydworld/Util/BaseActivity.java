package root.ydworld.Util;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by root1 on 2017. 10. 9..
 */

public class BaseActivity extends AppCompatActivity{


    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
