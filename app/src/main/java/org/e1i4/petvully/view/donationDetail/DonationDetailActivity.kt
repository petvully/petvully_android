package org.e1i4.petvully.view.donationDetail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.data.remote.model.DonationGoods
import org.e1i4.petvully.databinding.ActivityDonationDetailBinding


class DonationDetailActivity : BaseActivity<ActivityDonationDetailBinding>(ActivityDonationDetailBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
    }
    private fun setView(){
        val data = intent.getSerializableExtra("data") as DonationGoods

        binding.include.root.background = ColorDrawable(resources.getColor(R.color.color_primary, null))
        binding.include.divider.background = ColorDrawable(resources.getColor(R.color.color_primary, null))

        binding.include.category.text = data.category
        binding.include.coin.text = "${data.price} 불리코인"
        binding.include.title.text = data.name


        var requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(32))
        Glide.with(this)
            .load(data.imageUrl)
            .apply(requestOptions)
            .into(binding.include.img)


        binding.lineReview.background = ColorDrawable(resources.getColor(R.color.light_gray2, null))

        binding.tabDetail.setOnClickListener{
            binding.lineDetail.background = ColorDrawable(resources.getColor(R.color.white, null))
            binding.lineReview.background = ColorDrawable(resources.getColor(R.color.light_gray2, null))
            binding.detailInfo.visibility = View.VISIBLE
            binding.detailReview.visibility = View.GONE
        }
        binding.tabReview.setOnClickListener{
            binding.lineDetail.background = ColorDrawable(resources.getColor(R.color.light_gray2, null))
            binding.lineReview.background = ColorDrawable(resources.getColor(R.color.white, null))
            binding.detailInfo.visibility = View.GONE
            binding.detailReview.visibility = View.VISIBLE
//            binding.root.setLayoutParams(
//                RelativeLayout.LayoutParams(
//                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
//                )
//            )
        }
    }

}