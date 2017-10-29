package root.ydworld.Activity

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import root.ydworld.R
import root.ydworld.Util.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(this@MainActivity, PopService::class.java)
        startService(intent)

        button_bug.setOnClickListener {
            startActivity(Intent(this, BugActivity::class.java))
        }
    }
}
