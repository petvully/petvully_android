package org.e1i4.petvully.view.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivitySignInBinding
import org.e1i4.petvully.view.main.MainActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(ActivitySignInBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIntent()
    }

    private fun setIntent(){
        binding.btnLogin.setOnClickListener{
            // 임시로 main 이동
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}