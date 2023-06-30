package org.e1i4.petvully.view.main

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.databinding.FragmentAdoptionBinding
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.adapter.PetSoonAdapter
import org.e1i4.petvully.view.adapter.PetWaitingAdapter
import org.e1i4.petvully.view.message.MessageActivity
import org.e1i4.petvully.view.viewmodel.PetInfoViewModel

@AndroidEntryPoint
class AdoptionFragment : BaseFragment<FragmentAdoptionBinding>(FragmentAdoptionBinding::inflate) {
    private var petSoonAdapter = PetSoonAdapter()
    private var petWaitingAdapter = PetWaitingAdapter()
    private val viewModel: PetInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdoptionBinding.inflate(layoutInflater)
        initAdapter()
        setIntent()
        initObserver()
        setDialog()
        viewModel.requestPetList()

        return binding.root
    }

    private fun initAdapter() {
        binding.rvPetsSoon.adapter = petSoonAdapter
        binding.rvPetsWaiting.adapter = petWaitingAdapter
    }

    private fun initObserver() {
        viewModel.petList.observe(viewLifecycleOwner) { petList ->
            with(binding.rvPetsSoon.adapter as PetSoonAdapter) {
                setPetListData(petList)
            }
        }

        viewModel.petList.observe(viewLifecycleOwner) { petList ->
            with(binding.rvPetsWaiting.adapter as PetWaitingAdapter) {
                setPetListData(petList)
            }
        }
    }

    private fun setIntent() {
        val fragment = AdoptionSearchFragment()
        binding.etSearch.isFocusable = false
        binding.etSearch.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.main_fragment_container, fragment)?.commit()
        }

        binding.icMenu.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }

        binding.icMsg.setOnClickListener {
            startActivity(Intent(context, MessageActivity::class.java))
        }

        binding.icBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this)?.commit()
        }

    }

    private fun setDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_pet_info, null)
        val alertDialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        val dialogView2 = layoutInflater.inflate(R.layout.dialog_coin, null)
        val alertDialog2 = AlertDialog.Builder(context)
            .setView(dialogView2)
            .create()

        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경을 투명하게
        alertDialog2.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경을 투명하게

        binding.clWhole.setOnClickListener {
            alertDialog.show()
        }

        binding.clWhole.setOnClickListener {
            alertDialog.show()
        }

        dialogView.setOnClickListener{
            alertDialog.dismiss()
            alertDialog2.show()
        }

        dialogView2.setOnClickListener {
            alertDialog2.dismiss()
        }
    }
}