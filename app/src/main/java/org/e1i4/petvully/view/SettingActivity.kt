package org.e1i4.petvully.view

import android.content.Intent
import android.os.Bundle
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivitySettingBinding

class SettingActivity  : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setIntent()
    }

    private fun setIntent(){
         binding.clOpensource.setOnClickListener {
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }
    }
}