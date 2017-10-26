package root.ydworld.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by root1 on 2017. 10. 9..
 */

public class ClipBoardService extends Service {
//
//    private ClipboardManager clipboardManager;
//    private String url = "";
//    private String type = "mp4";
//
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
//        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
//            @Override
//            public void onPrimaryClipChanged() {
//                String clipText = clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
//                if(clipText.contains(getString(R.string.youtube_url_id))){
//                    Log.e("xxx", "yes!");
//                    url = clipText;
//                    setData();
//                    getData();
//                }
//            }
//        });
//
//        IntentFilter downloadCompleteFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
//        downloadCompleteFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
//        registerReceiver(downloadBroadcastReceiver, downloadCompleteFilter);
//
//    }
//
//    private BroadcastReceiver downloadBroadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if(intent.getAction() == DownloadManager.ACTION_NOTIFICATION_CLICKED){
//                Intent showDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
//                startActivity(showDownloadIntent);
//            }else{
//
//            }
//        }
//    };
//
//    private View dowmnloadView;
//    private WindowManager windowManager;
//    TextView titleText, contentTypeText;
//    Switch contentTypeSwitch;
//    ImageView sumImage;
//
//    private void createDownloadDialog(){
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                PixelFormat.TRANSLUCENT
//        );
//        params.gravity = Gravity.BOTTOM;
//
//        windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
//        windowManager.addView(dowmnloadView, params);
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if(dowmnloadView != null){
//                    windowManager.removeView(dowmnloadView);
//                }
//            }
//        }, 1000 * 8);
//    }
//
//    private void getData(){
//        RetrofitClass.getInstance().api
//                .getDownloadPath(url).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e("xxx", "data : " + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//        //titleText.setText(url);
//        //showDownloadDialog();
//    }
//
//    private void showDownloadDialog(){
//        if(checkOverlay()){
//            createDownloadDialog();
//        }
//    }
//
//    private boolean checkOverlay(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!Settings.canDrawOverlays(this)) {
//                showToast(getString(R.string.overlay_explane_text), true);
//                startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null)));
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void setData(){
//        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//        dowmnloadView = inflater.inflate(R.layout.view_download, null);
//
//        titleText = (TextView) dowmnloadView.findViewById(R.id.titleText);
//        contentTypeSwitch = (Switch) dowmnloadView.findViewById(R.id.contentTypeSwitch);
//        contentTypeText = (TextView) dowmnloadView.findViewById(R.id.contentTypeText);
//        sumImage = (ImageView) dowmnloadView.findViewById(R.id.sumImage);
//
//        Button downButton = (Button) dowmnloadView.findViewById(R.id.downButton);
//
//        contentTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                String contentTypeStr = "Video";
//                if(b){
//                    type = "mp4";
//                }else{
//                    type = "mp3";
//                    contentTypeStr = "Music";
//                }
//                contentTypeText.setText(contentTypeStr);
//            }
//        });
//
//        downButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showToast("Download Start", false);
//                windowManager.removeView(dowmnloadView);
//                dowmnloadView = null;
//
//                contentDownload("" + System.currentTimeMillis());
//            }
//        });
//    }
//
//    private void contentDownload(String title){
//        DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
//        String downloadApi = "http://52.14.74.219:9024/search?" + "url=" + url + "&" + "type" + type;
//        Uri downloadUri = Uri.parse(downloadApi);
//        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
//        request.setTitle("PT_" + title + "." + type);
//        request.setDescription("PopTube");
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "PT_" + title + "." + type);
//        //Log.d("xxx", "contentDownload: " + "PT_" + title + "." + type);
//        long downloadId = downloadManager.enqueue(request);
//    }
//
//    private void showToast(String msg, boolean isLong){
//        int length = Toast.LENGTH_SHORT;
//        if(isLong){
//            length = Toast.LENGTH_LONG;
//        }
//        Toast.makeText(this, msg, length).show();
//    }
//
}
