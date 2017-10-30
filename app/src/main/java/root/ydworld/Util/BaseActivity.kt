package root.ydworld.Util

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by root1 on 2017. 10. 9..
 */

open class BaseActivity : AppCompatActivity() {

    val pref: SharedPreferences
        get() = getSharedPreferences("pref", Context.MODE_PRIVATE)


    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    public fun setCookie(cookie: String){
        pref?.edit()?.putString("cookie", cookie)
        pref?.edit()?.commit()
    }

    public fun getCookie(): String{
        return pref!!.getString("cookie", "")
    }


}
