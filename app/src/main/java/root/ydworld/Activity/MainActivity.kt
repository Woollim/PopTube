package root.ydworld.Activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
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

            createDialog("환영합니다.", R.string.info_seton)

            Connect.api?.id?.enqueue(object : Callback<JsonObject>{
                override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                    val id = response?.body()?.get("id")?.asString
                    id?.let {
                        GetPref(this@MainActivity).setCookie(id!!)
                    }
                }
            })

        }

        val intent = Intent(this@MainActivity, PopService::class.java)
        startService(intent)

        button_bug.setOnClickListener {
            startActivity(Intent(this, BugActivity::class.java))
        }

        helpButton.setOnClickListener {
            createDialog("확인하세요.", R.string.info_permission)
        }
    }

    private fun createDialog(title: String, message: Int){
        val builder = AlertDialog.Builder(this)
                .setTitle(title).setMessage(message)
                .setPositiveButton("확인"){dialog, _ -> dialog.cancel()}
        builder.create().show()
    }
}
