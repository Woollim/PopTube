package root.ydworld.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
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

        MobileAds.initialize(this, getString(R.string.add_id))
        adView.loadAd(AdRequest.Builder().
                addTestDevice("7A34834041D0C1ABB8133B1CEFD3349D").build())

        adView.adListener = object : AdListener(){
            override fun onAdFailedToLoad(p0: Int) {
                Log.d("xxx", ""+p0)
            }
        }

    }

    override fun onStop() {
        super.onStop()
        progressBar.visibility = View.GONE
    }
}