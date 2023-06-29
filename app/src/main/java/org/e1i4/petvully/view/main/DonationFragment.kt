package org.e1i4.petvully.view.main

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
            Toast.makeText(requireContext(), "click!",Toast.LENGTH_SHORT).show()
        }
        binding.rvDonation.adapter = donationAdapter

        mockData()
        return binding.root
    }
    fun mockData(){
        donationAdapter.setData(
            listOf(
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
                DonationGoods("https://picsum.photos/200","배변패드","요요쉬",100),
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
            binding.give.setOnClickListener{
                onClickListener?.invoke(data)
            }
        }

    }
}