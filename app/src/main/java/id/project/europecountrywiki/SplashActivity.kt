package id.project.europecountrywiki

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.project.europecountrywiki.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var bindingSplash: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplash = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bindingSplash.root)

        bindingSplash.splashIcon.animate().setDuration(2000).alpha(1f).withEndAction {
            val intentMain = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intentMain)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}