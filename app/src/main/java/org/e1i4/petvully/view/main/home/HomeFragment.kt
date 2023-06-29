package org.e1i4.petvully.view.main.home

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.data.local.model.HomeArea
import org.e1i4.petvully.databinding.FragmentHomeBinding
import org.e1i4.petvully.util.helper.home.TransferPosition


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

        setHomeArea()




        return binding.root
    }


    private fun setSurfaceView(){
//        binding.homeSurfaceView.apply {
//            holder.addCallback(this)
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setHomeArea(){
        binding.background.setOnTouchListener { _: View, event: MotionEvent ->
            vm.goTo(binding.dog, event)
            true
        }


        binding.background.viewTreeObserver.addOnGlobalLayoutListener {
            val transfer = TransferPosition(
                Point(382, 714),
                Point(binding.background.width, binding.background.height)
            )
            vm.setHomeArea(listOf(
                HomeArea(
                    true,
                    0,
                    listOf(
                        transfer.toViewPoint(Point(233, 372)),
                        transfer.toViewPoint(Point(0, 541)),
                        transfer.toViewPoint(Point(0, 700)),
                        transfer.toViewPoint(Point(382, 70)),
                        transfer.toViewPoint(Point(382, 514)),
                    )
                )
            ))

        }
    }
}