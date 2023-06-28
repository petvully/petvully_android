package org.e1i4.petvully.view.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate){
    private val vm: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setSurfaceView()
        return binding.root
    }

    private fun setSurfaceView(){
        binding.homeSurfaceView.apply {
            holder.addCallback(this)
        }
    }
}