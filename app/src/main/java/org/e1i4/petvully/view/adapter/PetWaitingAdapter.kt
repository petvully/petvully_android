package org.e1i4.petvully.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.e1i4.petvully.data.local.PetSoonData
import org.e1i4.petvully.data.local.PetWaitingData
import org.e1i4.petvully.databinding.ItemPetsSoonBinding
import org.e1i4.petvully.databinding.ItemPetsWaitingBinding

class PetWaitingAdapter : RecyclerView.Adapter<PetWaitingAdapter.PetViewHolder>() {
    var petList = mutableListOf<PetWaitingData>()

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
        fun onBind(data: PetWaitingData) {
            binding.tvKind.text = data.kind
            binding.tvSex.text = data.sex
            binding.tvAge.text = data.age
            binding.tvLocation.text = data.location
        }
    }
}