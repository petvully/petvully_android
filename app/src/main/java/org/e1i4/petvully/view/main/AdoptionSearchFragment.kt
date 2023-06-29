package org.e1i4.petvully.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.databinding.FragmentAdoptionBinding
import org.e1i4.petvully.databinding.FragmentAdoptionSearchBinding

@AndroidEntryPoint
class AdoptionSearchFragment: BaseFragment<FragmentAdoptionSearchBinding>(FragmentAdoptionSearchBinding::inflate){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdoptionSearchBinding.inflate(layoutInflater)
        setIntent()

        return binding.root
    }

    private fun setIntent() {
        binding.icBack.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this)?.commit()
        }
    }
}