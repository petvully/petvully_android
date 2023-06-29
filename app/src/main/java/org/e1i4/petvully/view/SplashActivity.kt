package org.e1i4.petvully.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivitySplashBinding
import org.e1i4.petvully.view.sign.SignInActivity

@AndroidEntryPoint
class SplashActivity  : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moveSignActivity(2)
    }

    private fun moveSignActivity(sec: Int){
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, 1000 * sec.toLong())
    }
}