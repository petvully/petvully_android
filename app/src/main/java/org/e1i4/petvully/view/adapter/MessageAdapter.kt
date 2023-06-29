package org.e1i4.petvully.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.e1i4.petvully.data.local.MessageData
import org.e1i4.petvully.data.local.PetSoonData
import org.e1i4.petvully.databinding.ItemMessagesBinding
import org.e1i4.petvully.databinding.ItemPetsSoonBinding

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    var messageList = mutableListOf<MessageData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageViewHolder {
        val binding = ItemMessagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBind(messageList[position])
    }

    override fun getItemCount(): Int = messageList.size

    inner class MessageViewHolder(private val binding: ItemMessagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MessageData) {
            binding.tvName.text = data.name
            binding.tvContent.text = data.content
            binding.tvTime.text = data.time
        }
    }
}