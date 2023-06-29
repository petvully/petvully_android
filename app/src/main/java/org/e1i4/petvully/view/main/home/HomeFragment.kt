package org.e1i4.petvully.view.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Point
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.data.local.model.HomeArea
import org.e1i4.petvully.databinding.FragmentHomeBinding
import org.e1i4.petvully.util.helper.home.TransferPosition
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.main.AdoptionSearchFragment
import org.e1i4.petvully.view.message.MessageActivity


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val vm: HomeViewModel by activityViewModels()
    private lateinit var fabs: List<ImageView>
    private var fabIsOpen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setView()
        setSurfaceView()
        setClick()
        setHomeArea()
        setIntent()
        return binding.root
    }

    private fun setIntent() {

        binding.menuHeart.setOnClickListener {
            startActivity(Intent(context, MessageActivity::class.java))
        }

        binding.menuMenu.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }

    }

    private fun setView(){
        fabs = listOf(
            binding.fabWater,
            binding.fabFeed,
            binding.fabTouch,
            binding.fabShower,
            binding.fabWalk,
        )
        //TODO("fixed real data")
        val name = "봉쥬르"
        binding.tvAppbarTitle.text = SpannableString(String.format(getString(R.string.home_appbar_hello), name)).apply {
            setSpan(
                ForegroundColorSpan(resources.getColor(R.color.color_primary, null)),
                6,
                6 + name.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                StyleSpan(Typeface.BOLD),
                6,
                this.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.tvAppbarSubtitle.text = SpannableString(getString(R.string.home_appbar_subtitle)).apply {
            setSpan(ForegroundColorSpan(resources.getColor(R.color.color_primary,null)),4,8,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val level = 1.6
        binding.homeCurrentLevel.text = "Lv."+level.toInt()
        binding.homeNextLevel.text = "Lv."+(level.toInt()+1)
        binding.homeLevelProgress.max = 10
        binding.homeLevelProgress.progress = (level*10).toInt()%10

    }
    private fun setClick() {
        binding.fabPlus.setOnClickListener {
            fabIsOpen = !fabIsOpen
            val arr = listOf(-1.5f,0f, -0.9f,-0.9f, 0f,-1.5f, 0.9f,-0.9f, 1.5f,0f)
            it.startAnimation(
                RotateAnimation(
                    if(fabIsOpen) 0f else 45f,if(fabIsOpen) 45f else 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                ).apply {
                    duration = 300
                    fillAfter = true
                }
            )
            for (i in 0 until 5*2 step(2)) {
                fabs[i/2].startAnimation(
                    TranslateAnimation(
                        Animation.RELATIVE_TO_SELF,
                        if(fabIsOpen) 0f else arr[i],
                        Animation.RELATIVE_TO_SELF,
                        if(fabIsOpen) arr[i] else 0f,
                        Animation.RELATIVE_TO_SELF,
                        if(fabIsOpen) 0f else arr[i+1],
                        Animation.RELATIVE_TO_SELF,
                        if(fabIsOpen) arr[i+1] else 0f
                    ).apply {
                        duration = 250L + i*10
                        fillAfter = true
                    }
                )
            }
        }

        binding.fabWater.setOnClickListener{

        }
        binding.fabFeed.setOnClickListener{

        }
        binding.fabTouch.setOnClickListener{

        }
        binding.fabShower.setOnClickListener{

        }
        binding.fabWalk.setOnClickListener{

        }
        binding.menuHeart.setOnClickListener{

        }
        binding.menuMenu.setOnClickListener{

        }
    }

    private fun setSurfaceView() {
//        binding.homeSurfaceView.apply {
//            holder.addCallback(this)
//        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setHomeArea() {
        binding.background.setOnTouchListener { _: View, event: MotionEvent ->
            vm.goTo(binding.dog, event)
            true
        }


        binding.background.viewTreeObserver.addOnGlobalLayoutListener {
            val transfer = TransferPosition(
                Point(990, 1556),
                Point(binding.background.width, binding.background.height)
            )
            vm.setHomeArea(
                listOf(
                    HomeArea(
                        true,
                        0,
                        listOf(
                            transfer.toViewPoint(Point(0,1270)),
                            transfer.toViewPoint(Point(0, 1556)),
                            transfer.toViewPoint(Point(990, 1556)),
                            transfer.toViewPoint(Point(980, 980)),
                            transfer.toViewPoint(Point(429,630)),
                        )
                    ),
                    HomeArea(
                        true,
                        1,
                        listOf(
                            transfer.toViewPoint(Point(390,655)),
                            transfer.toViewPoint(Point(621,767)),
                            transfer.toViewPoint(Point(913,711)),
                            transfer.toViewPoint(Point(645,524)),
                        )
                    )
                )
            )

        }
    }
}