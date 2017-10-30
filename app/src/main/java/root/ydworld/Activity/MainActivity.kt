package root.ydworld.Activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import root.ydworld.Connect.Connect
import root.ydworld.R
import root.ydworld.Util.BaseActivity
import root.ydworld.Util.GetPref

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(getCookie().isEmpty()){
            val builder = AlertDialog.Builder(this).setTitle("알립니다").setMessage(getString(R.string.info_seton))
                    .setPositiveButton("확인", DialogInterface.OnClickListener({
                        dialog, _ ->
                        dialog.cancel()
                    }))

            Connect.api?.id?.enqueue(object : Callback<JsonObject>{
                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                    val id = response?.body()?.get("id")?.asString
                    Log.d("xxx", id)
                    id?.let {
                        GetPref(this@MainActivity).setCookie(id!!)
                    }
                }
            })

            builder.create().show()
        }

        val intent = Intent(this@MainActivity, PopService::class.java)
        startService(intent)

        button_bug.setOnClickListener {
            startActivity(Intent(this, BugActivity::class.java))
        }
    }
}
