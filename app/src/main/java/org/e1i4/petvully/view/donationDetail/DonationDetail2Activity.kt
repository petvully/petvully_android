package org.e1i4.petvully.view.donationDetail

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.data.remote.model.DonationGoods
import org.e1i4.petvully.databinding.ActivityDonationDetail2Binding
import org.e1i4.petvully.databinding.ActivityDonationDetailBinding

class DonationDetail2Activity : BaseActivity<ActivityDonationDetail2Binding>(
    ActivityDonationDetail2Binding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
    }
    private fun setView(){
        val data = intent.getSerializableExtra("data") as DonationGoods

        binding.include.root.background = ColorDrawable(resources.getColor(R.color.color_primary, null))
        binding.include.give.visibility = View.INVISIBLE

        binding.include.category.text = data.category
        binding.include.coin.text = "${data.price} 불리코인"
        binding.include.title.text = data.name
    }

}