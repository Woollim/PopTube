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



}