package acr.browser.speedbrowser7g

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import java.util.*

class SplashActivity : AppCompatActivity() {

    private var adRespons = true
    private var runable: Runnable? = null
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val lastopen = prefs.getInt("lastopen", 0)

        val editor = prefs.edit()
        editor.putInt("lastopen", Date().day)
        editor.apply()

        if (lastopen == Date().day){
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            return
        }

        runable = Runnable {
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
        }

        startDelay(4000)
    }

    private fun startDelay(time: Long){
        handler.removeCallbacks(runable)
        handler.postDelayed(runable, time)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
