package root.ydworld.Activity;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import root.ydworld.Connect.CallResponce;
import root.ydworld.Connect.RetrofitClass;
import root.ydworld.R;

/**
 * Created by root1 on 2017. 10. 9..
 */

public class ClipBoardService extends Service {

    private ClipboardManager clipboardManager;
    private String url = "";
    private String type = "mp4";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                String clipText = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
                if(clipText.contains(getString(R.string.youtube_url_id))){
                    Log.e("xxx", "yes!");
                    url = clipText;
                    setData();
                    getData();
                }
            }
        });
    }

    private View dowmnloadView;
    private WindowManager windowManager;
    TextView titleText, contentTypeText;
    Switch contentTypeSwitch;
    ImageView sumImage;

    private void createDownloadDialog(){
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.BOTTOM;

        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        windowManager.addView(dowmnloadView, params);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(dowmnloadView != null){
                    windowManager.removeView(dowmnloadView);
                }
            }
        }, 1000 * 8);
    }

    private void getData(){
//        RetrofitClass.getInstance().api
//                .getTitle(url).enqueue(new CallResponce<String>(this) {
//            @Override
//            public void callBack(int code, String body) {
//                if(code == 200){
//                    titleText.setText(body);
//                    createDownloadDialog();
//                }
//            }
//        });
        titleText.setText(url);
        createDownloadDialog();
    }

    private void setData(){
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        dowmnloadView = inflater.inflate(R.layout.view_download, null);

        titleText = (TextView) dowmnloadView.findViewById(R.id.titleText);
        contentTypeSwitch = (Switch) dowmnloadView.findViewById(R.id.contentTypeSwitch);
        contentTypeText = (TextView) dowmnloadView.findViewById(R.id.contentTypeText);
        sumImage = (ImageView) dowmnloadView.findViewById(R.id.sumImage);

        Button downButton = (Button) dowmnloadView.findViewById(R.id.downButton);

        contentTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    type = "mp4";
                }else{
                    type = "mp3";
                }
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Download Start");
                windowManager.removeView(dowmnloadView);
                dowmnloadView = null;

                RetrofitClass.getInstance().api
                        .downloadVideo(url, type).enqueue(new CallResponce<ResponseBody>(ClipBoardService.this) {
                    @Override
                    public void callBack(int code, ResponseBody body) {
                        if(code == 200){
                            createFile(body.byteStream());
                        }
                    }
                });
            }
        });
    }

    private void createFile(InputStream fileInput){
        try{
            File appFolder = new File(Environment.getExternalStorageDirectory(), "PopTube");
            if(!appFolder.exists()){ appFolder.mkdir();}

            File saveFile = new File(appFolder, url + "." + type);
            saveFile.setWritable(true);
            OutputStream output = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            int size;

            while ((size = fileInput.read(buffer)) > 0){
                output.write(buffer, 0, size);
            }

            fileInput.close();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
