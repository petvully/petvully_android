package org.e1i4.petvully.view.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivityAdoptBinding
import org.e1i4.petvully.databinding.ActivityCoinBinding

class AdoptActivity : BaseActivity<ActivityAdoptBinding>(ActivityAdoptBinding::inflate) {
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