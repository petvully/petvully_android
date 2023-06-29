package org.e1i4.petvully.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.e1i4.petvully.data.local.PetSoonData
import org.e1i4.petvully.data.local.PetWaitingData
import org.e1i4.petvully.data.remote.model.ResponsePetsInfoItem
import org.e1i4.petvully.databinding.ItemPetsSoonBinding
import org.e1i4.petvully.databinding.ItemPetsWaitingBinding

class PetWaitingAdapter : RecyclerView.Adapter<PetWaitingAdapter.PetViewHolder>() {
    var petList = emptyList<ResponsePetsInfoItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetViewHolder {
        val binding = ItemPetsWaitingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.onBind(petList[position])
    }

    override fun getItemCount(): Int = petList.size

    inner class PetViewHolder(private val binding: ItemPetsWaitingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponsePetsInfoItem) {
            binding.tvKind.text = data.kind
            binding.tvSex.text = data.gender
            binding.tvAgeData.text = data.age.toString()
            binding.tvLocation.text = data.region

            Glide.with(itemView)
                .load(data.image)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(binding.ivPet)
        }
    }

    fun setPetListData(petList: List<ResponsePetsInfoItem>) {
        this.petList = petList.subList(4,9)
        notifyDataSetChanged()
    }
}