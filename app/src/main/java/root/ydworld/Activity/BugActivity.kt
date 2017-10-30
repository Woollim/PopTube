package root.ydworld.Activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bug.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import root.ydworld.Connect.Connect
import root.ydworld.R
import root.ydworld.Util.BaseActivity
import root.ydworld.Util.GetPref

/**
 * Created by root1 on 2017. 10. 27..
 */
class BugActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug)

        bug_button.setOnClickListener {
            val bugText = bug_edit.text.toString()
            if (bugText.isEmpty()){
                showToast("버그에 관한 정보를 입력하세요.")
            }else{
                Connect.api?.sendError(GetPref(this).getCookie(), bugText)
                        ?.enqueue(object : Callback<Void>{
                            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                                t?.printStackTrace()
                            }

                            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                                showToast(when(response?.code()){
                                    200 -> "버그를 전송하였습니다."
                                    400 -> "버그를 보내지 못했습니다."
                                    else -> "오류가 발생했습니다."
                                })
                            }
                        })
            }
        }


    }
}