package root.ydworld.Activity

import android.app.*
import android.content.*
import android.graphics.*
import android.os.*
import android.provider.*
import android.util.*
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.view_download.view.*
import root.ydworld.*
import java.util.*
import kotlin.concurrent.*


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
            Log.d("xxx", text)
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
                Toast.makeText(this, "오버레이 권한이 필요합니다.+\n+자세한 내용은 PopTube에서 확인하세요.", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    fun loadData(text : String){
        var downloadView : View? = findView(R.layout.view_download)
        with(downloadView!!){
            linkText.text = text

            downloadButton.setOnClickListener {
                windowManager?.removeView(downloadView)
                downloadView = null

                YTDownManager(this@PopService, text)
            }

            var timer = Timer()
            timer.schedule(timerTask {
                downloadView?.let {
                    windowManager?.removeView(downloadView)
                }
            }, 5 * 1000)
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