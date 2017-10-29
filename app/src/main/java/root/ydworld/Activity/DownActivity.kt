package root.ydworld.Activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_down.*
import root.ydworld.R
import root.ydworld.Util.BaseActivity

/**
 * Created by root1 on 2017. 10. 29..
 */
class DownActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down)
        val url = intent.getStringExtra("url")

        webView.loadUrl(url)
    }

    override fun onStop() {
        super.onStop()
        text.visibility = View.GONE
    }
}