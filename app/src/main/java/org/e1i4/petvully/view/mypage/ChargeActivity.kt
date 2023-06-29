package org.e1i4.petvully.view.mypage

import android.content.Intent
import android.os.Bundle
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivityChargeBinding

class ChargeActivity : BaseActivity<ActivityChargeBinding>(ActivityChargeBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIntent()
    }

    private fun setIntent(){
        binding.icBack.setOnClickListener{
            finish()
        }

        binding.btnCharge.setOnClickListener {
            startActivity(Intent(this, ChargingActivity::class.java))
        }
    }
}