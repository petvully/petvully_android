package org.e1i4.petvully.view.message

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.data.local.MessageData
import org.e1i4.petvully.data.local.PetSoonData
import org.e1i4.petvully.data.local.PetWaitingData
import org.e1i4.petvully.databinding.ActivityMessageBinding
import org.e1i4.petvully.view.adapter.MessageAdapter

class MessageActivity  : BaseActivity<ActivityMessageBinding>(ActivityMessageBinding::inflate) {
    private var messageAdapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIntent()
        initAdapter()
    }

    private fun setIntent() {
        binding.icBack.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter(){
        binding.rvMessage.adapter = messageAdapter

        messageAdapter.messageList.addAll(
            listOf(
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "1시간"),
                MessageData("천안 반려동물 보호센터", "회원님에게 사진을 보냈습니다.", "2시간"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "7시간"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "1일"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "1주일"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "1주일"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "1달전"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "1달전"),
                MessageData("펫불리 (PetVully)", "회원님에게 메시지를 보냈습니다.", "2달전"),

            )
        )

        messageAdapter.notifyDataSetChanged()
    }
}