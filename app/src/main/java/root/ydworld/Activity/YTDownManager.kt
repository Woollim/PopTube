package root.ydworld.Activity

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

/**
 * Created by root1 on 2017. 10. 26..
 */
class YTDownManager(context: Context, keyword: String, type: String) {

    init {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE)!! as DownloadManager
        val request = android.app.DownloadManager
                .Request(Uri.parse(getUrl(keyword, type)))
        request.setTitle("PopTube 영상 다운로드")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES,
                "PT_${System.currentTimeMillis()}.$type")
        downloadManager.enqueue(request)
    }

    private fun getUrl(keyword: String, type: String): String{
        return "http://52.14.74.219:9024/search?url=$keyword&type=$type"
    }


}