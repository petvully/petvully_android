package org.e1i4.petvully.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.data.local.PetSoonData
import org.e1i4.petvully.data.local.PetWaitingData
import org.e1i4.petvully.databinding.FragmentAdoptionBinding
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.adapter.PetSoonAdapter
import org.e1i4.petvully.view.adapter.PetWaitingAdapter
import org.e1i4.petvully.view.message.MessageActivity
import org.e1i4.petvully.view.sign.SignInActivity

@AndroidEntryPoint
class AdoptionFragment: BaseFragment<FragmentAdoptionBinding>(FragmentAdoptionBinding::inflate){
    private var petSoonAdapter = PetSoonAdapter()
    private var petWaitingAdapter = PetWaitingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdoptionBinding.inflate(layoutInflater)
        initAdapter()
        setIntent()

        return binding.root
    }

    private fun initAdapter(){
        binding.rvPetsSoon.adapter = petSoonAdapter

        petSoonAdapter.petList.addAll(
            listOf(
                PetSoonData("믹스견 ♂", "중성화 알 수 없음", "3살", "충청남도 홍성군"),
                PetSoonData("코숏 ♂", "중성화 알 수 없음", "1살 미만", "충청남도 당진시"),
                PetSoonData("믹스견 ♀", "중성화 알 수 없음", "1살 미만", "충청남도 서산시"),
            )
        )

        petSoonAdapter.notifyDataSetChanged()

        binding.rvPetsWaiting.adapter = petWaitingAdapter
        binding.rvPetsWaiting.layoutManager = GridLayoutManager(activity, 2)
        petWaitingAdapter.petList.addAll(
            listOf(
                PetWaitingData("믹스견 ♂", "중성화 알 수 없음", "3살", "충청남도 홍성군"),
                PetWaitingData("코숏 ♂", "중성화 알 수 없음", "1살 미만", "충청남도 당진시"),
                PetWaitingData("믹스견 ♀", "중성화 알 수 없음", "1살 미만", "충청남도 서산시"),
                PetWaitingData("믹스견 ♂", "중성화 알 수 없음", "3살", "충청남도 홍성군"),
                PetWaitingData("코숏 ♂", "중성화 알 수 없음", "1살 미만", "충청남도 당진시"),
                PetWaitingData("믹스견 ♀", "중성화 알 수 없음", "1살 미만", "충청남도 서산시"),
            )
        )

        petSoonAdapter.notifyDataSetChanged()
    }

    private fun setIntent() {
        val fragment = AdoptionSearchFragment()
        binding.etSearch.isFocusable = false
        binding.etSearch.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_fragment_container, fragment)?.commit()
        }

        binding.icMenu.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }

        binding.icHeart.setOnClickListener {
            startActivity(Intent(context, MessageActivity::class.java))
        }

        binding.icBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this)?.commit()
        }
    }
}