package root.ydworld.Activity

import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_down.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import root.ydworld.Connect.Connect
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
        adView.loadAd(AdRequest.Builder().build())

        Connect.api?.sendLink(getCookie(), url)
                ?.enqueue(object : Callback<Void>{
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {

                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {

                    }
                })

    }

    override fun onStop() {
        super.onStop()
        progressBar.visibility = View.GONE
    }
}