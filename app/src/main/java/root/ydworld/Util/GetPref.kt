package root.ydworld.Util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by root1 on 2017. 10. 29..
 */

public class GetPref(context: Context){

    public var pref: SharedPreferences? = null

    init {
        pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    public fun setCookie(cookie: String){
        val editor = pref?.edit()
        editor?.putString("cookie", cookie)
        editor?.commit()
    }

    public fun getCookie(): String{
        return pref!!.getString("cookie", "")
    }

}