package root.ydworld.Activity

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.view_download.view.*
import root.ydworld.R
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Created by root1 on 2017. 10. 26..
 */

public class PopService : Service(){

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(Context.WINDOW_SERVICE)!! as WindowManager

        val clipBoard = getSystemService(Context.CLIPBOARD_SERVICE)!! as ClipboardManager
        clipBoard.addPrimaryClipChangedListener {
            val text = clipBoard.primaryClip.getItemAt(0).text.toString()
            if(getString(R.string.youtube_url_id) in text){
                if(checkOverlay()){
                    loadData(text)
                }
            }
        }
    }

    fun checkOverlay(): Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!Settings.canDrawOverlays(this)){
                Toast.makeText(this, "오버레이 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        return true
    }

    fun loadData(text : String){
        Log.d("xxx", text)
        var downloadView : View? = findView(R.layout.view_download)
        with(downloadView!!){
            linkText.text = text
            var type = "mp4"
            typeSwitch.setOnCheckedChangeListener {
                _, checked ->
                type = if(checked) "mp4" else "mp3"
                typeText.text = if(checked) "VIDEO" else "MP3"
            }

            downloadButton.setOnClickListener {
                windowManager?.removeView(downloadView)
                downloadView = null
                YTDownManager(this@PopService, text, type)
            }

            var timer = Timer()
            timer.schedule(timerTask {
                downloadView?.let {
                    windowManager?.removeView(downloadView)
                }
            }, 7 * 1000)
        }

        param.gravity = Gravity.BOTTOM
        windowManager?.addView(downloadView, param)
    }

    private fun findView(id: Int) : View{
        val inflator = getSystemService(Context.LAYOUT_INFLATER_SERVICE)!! as LayoutInflater
        return inflator.inflate(id, null)
    }


    var windowManager : WindowManager? = null
    val param : WindowManager.LayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT)

}