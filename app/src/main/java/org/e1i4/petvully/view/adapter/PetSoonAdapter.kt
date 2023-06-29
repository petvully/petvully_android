package org.e1i4.petvully.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.e1i4.petvully.data.local.PetSoonData
import org.e1i4.petvully.databinding.ItemPetsSoonBinding

class PetSoonAdapter : RecyclerView.Adapter<PetSoonAdapter.PetViewHolder>() {
    var petList = mutableListOf<PetSoonData>()

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
        fun onBind(data: PetSoonData) {
            binding.tvKind.text = data.kind
            binding.tvSex.text = data.sex
            binding.tvAge.text = data.age
            binding.tvLocation.text = data.location
        }
    }
}