package org.e1i4.petvully.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.databinding.FragmentAdoptionBinding
import org.e1i4.petvully.databinding.FragmentMypageBinding
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.mypage.AdoptActivity
import org.e1i4.petvully.view.mypage.ChargeActivity
import org.e1i4.petvully.view.mypage.CoinActivity
import org.e1i4.petvully.view.mypage.DonateActivity
import org.e1i4.petvully.view.mypage.RealAdoptActivity

@AndroidEntryPoint
class MypageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMypageBinding.inflate(layoutInflater)
        setIntent()

        return binding.root
    }

    private fun setIntent() {
        binding.clCharge.setOnClickListener {
            startActivity(Intent(context, ChargeActivity::class.java))
        }

        binding.clMenu1.setOnClickListener {
            startActivity(Intent(context, CoinActivity::class.java))
        }

        binding.clMenu2.setOnClickListener {
            startActivity(Intent(context, AdoptActivity::class.java))
        }

        binding.clMenu3.setOnClickListener {
            startActivity(Intent(context, RealAdoptActivity::class.java))
        }

        binding.clMenu4.setOnClickListener {
            startActivity(Intent(context, DonateActivity::class.java))
        }

    }
}