package root.ydworld.Activity

import android.content.Context
import android.content.Intent
import root.ydworld.R

/**
 * Created by root1 on 2017. 10. 26..
 */
class YTDownManager(context: Context, keyword: String) {

    var youtube_url_id = ""
    var down_youtube_link = ""

    init {
        youtube_url_id = context.getString(R.string.youtube_url_id)
        down_youtube_link = context.getString(R.string.youtube_base_url)

        val intent = Intent(context, DownActivity::class.java)
        intent.putExtra("url", getUrl(keyword))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun getUrl(link: String): String{
        val keyword = link.split(youtube_url_id)
        return down_youtube_link + keyword[1]
    }


}
