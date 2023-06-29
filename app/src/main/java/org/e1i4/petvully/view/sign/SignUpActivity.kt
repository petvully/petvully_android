package org.e1i4.petvully.view.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivitySignUpBinding
import org.e1i4.petvully.view.main.MainActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIntent()
    }

    private fun setIntent() {
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        binding.icBack.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}