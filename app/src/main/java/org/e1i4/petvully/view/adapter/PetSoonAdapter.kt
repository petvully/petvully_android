package org.e1i4.petvully.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.e1i4.petvully.data.remote.model.ResponsePetsInfoItem
import org.e1i4.petvully.databinding.ItemPetsSoonBinding

class PetSoonAdapter : RecyclerView.Adapter<PetSoonAdapter.PetViewHolder>() {
    // var listener: ((ResponsePetsInfoItem, Int) -> Unit)? = null
    var petList = emptyList<ResponsePetsInfoItem>()
    // var mPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetViewHolder {
        val binding = ItemPetsSoonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.onBind(petList[position])
    }

    override fun getItemCount(): Int = petList.size

    inner class PetViewHolder(private val binding: ItemPetsSoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponsePetsInfoItem) {
            binding.tvKind.text = data.kind
            binding.tvSex.text = data.gender
            binding.tvAgeData.text = data.age.toString()
            binding.tvLocation.text = data.region

            Glide.with(itemView)
                .load(data.image)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.ivPetSoon)
        }
    }

    fun setPetListData(petList: List<ResponsePetsInfoItem>) {
        this.petList = petList.subList(0,3)
        notifyDataSetChanged()
    }
}