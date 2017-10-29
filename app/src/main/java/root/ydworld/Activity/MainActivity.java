package root.ydworld.Activity;

import android.content.Intent;
import android.os.Bundle;

import root.ydworld.R;
import root.ydworld.Util.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, PopService.class);
        startService(intent);
    }
}
