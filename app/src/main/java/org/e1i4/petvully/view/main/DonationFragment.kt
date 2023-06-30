package org.e1i4.petvully.view.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.data.remote.model.DonationGoods
import org.e1i4.petvully.databinding.FragmentDonationBinding
import org.e1i4.petvully.databinding.RowDonationBinding
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.donationDetail.DonationDetailActivity
import org.e1i4.petvully.view.message.MessageActivity


@AndroidEntryPoint
class DonationFragment : BaseFragment<FragmentDonationBinding>(FragmentDonationBinding::inflate){
    private  lateinit var  donationAdapter: DonationAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        donationAdapter = DonationAdapter()
        donationAdapter.onClickListener = {
            startActivity(Intent(requireContext(),DonationDetailActivity::class.java).putExtra("data",it))
        }
        binding.rvDonation.adapter = donationAdapter

        mockData()
        setIntent()
        return binding.root
    }

    private fun setIntent() {
        binding.icHeart.setOnClickListener {
            startActivity(Intent(context, MessageActivity::class.java))
        }

        binding.icMenu.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }

    }

    fun mockData(){
        donationAdapter.setData(
            listOf(
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/a96f336c-30c0-4650-9287-087eca3e7cdc","배변패드","요요쉬",50),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/0e4fe2bc-47d1-4472-be79-51e3993ddb4d","배변패드","정직한 패드 100매",100),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/43e46d4e-6e61-440a-a027-b73492c0fb21","펫 사료","로얄캐닌 3kg",300),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/e7705622-e2f7-45a9-ad36-8734d1f069ec","펫 간식","시저캔",20),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/a96f336c-30c0-4650-9287-087eca3e7cdc","배변패드","요요쉬",50),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/0e4fe2bc-47d1-4472-be79-51e3993ddb4d","배변패드","정직한 패드 100매",100),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/43e46d4e-6e61-440a-a027-b73492c0fb21","펫 사료","로얄캐닌 3kg",300),
                DonationGoods("https://github.com/CareSpoon/CareSpoon_Android/assets/63237214/e7705622-e2f7-45a9-ad36-8734d1f069ec","펫 간식","시저캔",20),
            )
        )
    }
}
class DonationAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val data: ArrayList<DonationGoods> = arrayListOf()
    var onClickListener: ((doc: DonationGoods) -> Unit)? = null

    fun setData(data: List<DonationGoods>) {
        val prev = this.data.size
        this.data.clear()
        notifyItemRangeRemoved(0, prev)

        this.data.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DonationViewHolder(
            RowDonationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DonationViewHolder).bind(data[position])
        holder.onClickListener = onClickListener
    }
    override fun getItemCount(): Int {
        return data.size
    }

    inner class DonationViewHolder(private val binding:RowDonationBinding): RecyclerView.ViewHolder(binding.root){
        var onClickListener: ((doc: DonationGoods) -> Unit)? = null
        var requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(32))
        fun bind(data: DonationGoods){
            binding.category.text = data.category
            binding.coin.text = "${data.price} 불리코인"
            binding.title.text = data.name


            Glide.with(binding.img)
                .load(data.imageUrl)
                .apply(requestOptions)
                .into(binding.img)
            binding.root.setOnClickListener{
                onClickListener?.invoke(data)
            }
        }

    }
}