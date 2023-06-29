package org.e1i4.petvully.view.mypage

import android.os.Bundle
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivityCoinBinding

class CoinActivity : BaseActivity<ActivityCoinBinding>(ActivityCoinBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIntent()
    }

    private fun setIntent(){
        binding.icBack.setOnClickListener{
            finish()
        }
    }
}