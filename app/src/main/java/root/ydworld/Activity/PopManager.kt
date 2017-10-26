package root.ydworld.Activity

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
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

            }
        }
    }

    fun loadData(text : String){
        var downloadView : View? = findView(R.layout.view_download)
        with(downloadView!!){
            titleText.text = text
            contentTypeSwitch.setOnCheckedChangeListener {
                _, checked ->
                contentTypeText.text = if(checked) "Video" else "Mp3"
            }

            downButton.setOnClickListener {
                windowManager?.removeView(downloadView)
                downloadView = null
                Log.d("xxx", "down Start")
            }

            var timer = Timer()
            timer.schedule(timerTask {
                downloadView?.let {
                    windowManager?.removeView(downloadView)
                }
            }, 3 * 1000)
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
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT)

}