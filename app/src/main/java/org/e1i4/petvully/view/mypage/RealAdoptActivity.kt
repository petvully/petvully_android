package org.e1i4.petvully.view.mypage

import android.content.Intent
import android.os.Bundle
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivityRealAdoptBinding
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.message.MessageActivity

class RealAdoptActivity : BaseActivity<ActivityRealAdoptBinding>(ActivityRealAdoptBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIntent()
    }

    private fun setIntent() {
        binding.icBack.setOnClickListener {
            finish()
        }

        binding.icMenu.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        binding.icMsg.setOnClickListener {
            startActivity(Intent(this, MessageActivity::class.java))
        }

    }
}