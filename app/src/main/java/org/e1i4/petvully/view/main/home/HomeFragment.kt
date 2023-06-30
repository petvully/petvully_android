package org.e1i4.petvully.view.main.home

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseFragment
import org.e1i4.petvully.data.local.model.HomeArea
import org.e1i4.petvully.data.remote.PVService
import org.e1i4.petvully.data.remote.model.UserRemote
import org.e1i4.petvully.databinding.FragmentHomeBinding
import org.e1i4.petvully.util.helper.home.TransferPosition
import org.e1i4.petvully.view.SettingActivity
import org.e1i4.petvully.view.main.AdoptionSearchFragment
import org.e1i4.petvully.view.message.MessageActivity
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    @Inject
    lateinit var service: PVService
    private val vm: HomeViewModel by activityViewModels()
    private lateinit var fabs: List<ImageView>
    private lateinit var exp: List<View>
    private var fabIsOpen = false
    private val fabOpenList = arrayOf(true, true, true, true, true)
    private lateinit var user: UserRemote
    private var ing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setView()
        setClick()
        setHomeArea()
        setIntent()
        scheduling()
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main){
                showingChat(resources.getStringArray(R.array.home_chat_before)[0])
            }
        }
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

    private fun setView() {
        fabs = listOf(
            binding.fabWater,
            binding.fabFeed,
            binding.fabTouch,
            binding.fabShower,
            binding.fabWalk,
        )
        exp = listOf(
            binding.exp1,
            binding.exp2,
            binding.exp3,
            binding.exp4,
            binding.exp5,
        )
        CoroutineScope(Dispatchers.IO).launch {
            val js = service.questInfo(1)[0]
            user = Gson().fromJson(js.get("userId"), UserRemote::class.java)
            withContext(Dispatchers.Main) {
                updateProfile()

                binding.tvAppbarTitle.text = SpannableString(
                    String.format(
                        getString(R.string.home_appbar_hello),
                        user.nickname
                    )
                ).apply {
                    setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.color_primary, null)),
                        6,
                        6 + user.nickname.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        StyleSpan(Typeface.BOLD),
                        6,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                binding.tvAppbarSubtitle.text =
                    SpannableString(getString(R.string.home_appbar_subtitle)).apply {
                        setSpan(
                            ForegroundColorSpan(resources.getColor(R.color.color_primary, null)),
                            4,
                            8,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }


                if (js.get("water").asBoolean) {
                    disableFab(0)
                    fabOpenList[0] = false
                }
                if (js.get("food").asBoolean) {
                    disableFab(1)
                    fabOpenList[1] = false
                }
                if (js.get("touch").asBoolean) {
                    disableFab(2)
                    fabOpenList[2] = false
                }
                if (js.get("shower").asBoolean) {
                    disableFab(3)
                    fabOpenList[3] = false
                }
                if (js.get("walk").asBoolean) {
                    disableFab(4)
                    fabOpenList[4] = false
                }
            }
        }
    }

    private fun setLevel(level: Int) {
        binding.homeCurrentLevel.text = "Lv." + level / 10
        binding.homeNextLevel.text = "Lv." + (level / 10 + 1)
        for (i in 0 until level % 10)
            exp[i].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ff92153a"))
        for (i in level % 10 until 5)
            exp[i].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#fffcecec"))
    }

    private fun disableFab(index: Int) {
        fabs[index].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.light_gray2,null))
    }

    private fun setClick() {
        binding.fabPlus.setOnClickListener {
            fabIsOpen = !fabIsOpen
            val arr = arrayOf(-1.5f, 0f, -0.9f, -0.9f, 0f, -1.5f, 0.9f, -0.9f, 1.5f, 0f)
//            for(i in arr.indices)
//                arr[i] =arr[i]*300
            it.startAnimation(
                RotateAnimation(
                    if (fabIsOpen) 0f else 45f,
                    if (fabIsOpen) 45f else 0f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                ).apply {
                    duration = 300
                    fillAfter = true
                }
            )
            for (i in 0 until 5 * 2 step (2)) {
                ObjectAnimator.ofFloat(fabs[i/2], "translationX",
                    fabs[i/2].width * if (fabIsOpen) 0f else arr[i],
                    fabs[i/2].width * if (fabIsOpen) arr[i] else 0f,).apply {
                    duration = 250L + i * 10
                    start()
                }
                ObjectAnimator.ofFloat(fabs[i/2], "translationY",
                    fabs[i/2].width * if (fabIsOpen) 0f else arr[i + 1],
                    fabs[i/2].width * if (fabIsOpen) arr[i + 1] else 0f).apply {
                    duration = 250L + i * 10
                    start()
                }


//                fabs[i / 2].startAnimation(
//                    TranslateAnimation(
//                        Animation.RELATIVE_TO_SELF,
//                        if (fabIsOpen) 0f else arr[i],
//                        Animation.RELATIVE_TO_SELF,
//                        if (fabIsOpen) arr[i] else 0f,
//                        Animation.RELATIVE_TO_SELF,
//                        if (fabIsOpen) 0f else arr[i + 1],
//                        Animation.RELATIVE_TO_SELF,
//                        if (fabIsOpen) arr[i + 1] else 0f
//                    ).apply {
//                        duration = 250L + i * 10
//                        fillAfter = true
//                    }
//                )
            }
        }
        for(i in fabs.indices){
            fabs[i].setOnClickListener{
                if(!fabOpenList[i])
                    return@setOnClickListener
                disableFab(i)
                CoroutineScope(Dispatchers.IO).launch {
                    when (i) {
                        0 -> service.giveWater(1)
                        1 -> service.giveFood(1)
                        2 -> {
                            withContext(Dispatchers.Main) {
                                handTool(true)
                            }
                            try{
                                service.giveTouch(1)
                            }
                            catch (e:Exception){
                                null
                            }

                        }
                        3 -> {
                            withContext(Dispatchers.Main){
                                handTool(false)
                            }
                            try{
                                service.giveShower(1)
                            }
                            catch (e:Exception){
                                null
                            }
                        }
                        4 -> service.giveWalk(1)
                        else -> null
                    }?.run {
                        ing = true
                        user = this
                            delay(3000)
                            withContext(Dispatchers.Main){
                                ing = false
                                updateProfile()
                            }
                    }
                }
            }
        }
    }
    private fun updateProfile(){
        setLevel(user.level * 10 + user.exp / 20)
        binding.tvCoin.text = user.coin.toString()
    }

    private fun scheduling(){
        CoroutineScope(Dispatchers.IO).launch {
            while (true){
                delay(4000)
                (Math.random()*5).toInt().let {
                    if(fabOpenList[it]){
                        withContext(Dispatchers.Main){
                            binding.dog.setImageResource(randomPose())
                            showingChat(resources.getStringArray(R.array.home_chat_before)[(Math.random()*3).toInt()])
                        }
                        delay(2000)
                        if(!ing)
                            binding.chatLayout.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
    private fun showingChat(str:String){
        binding.chatLayout.visibility = View.VISIBLE
        binding.chat.text = str
    }
    private fun randomPose(): Int{
        (Math.random()*5).toInt().let {
            return when(it){
                0 -> R.drawable.img_pet1
                1 -> R.drawable.img_pet2
                2 -> R.drawable.img_pet3
                3 -> R.drawable.img_pet4
                else -> R.drawable.img_pet5
            }
        }
    }
    private fun handTool(isTouch:Boolean){
        binding.hand.setImageResource(if(isTouch) R.drawable.touch else R.drawable.shower)
        binding.hand.visibility = View.VISIBLE
        if(isTouch){
            showingChat(resources.getStringArray(R.array.home_chat_ing)[0])
        }else{
            showingChat(resources.getStringArray(R.array.home_chat_ing)[1])
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(5500)
            withContext(Dispatchers.Main){
                binding.hand.visibility = View.INVISIBLE
                showingChat(resources.getStringArray(R.array.home_chat_before)[0])
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setHomeArea() {
        binding.background.setOnTouchListener { _: View, event: MotionEvent ->
            when(event.action) {
                MotionEvent.ACTION_MOVE -> {
                    binding.hand.x = event.x
                    binding.hand.y = event.y
                }
            }
            true
        }


//        binding.background.viewTreeObserver.addOnGlobalLayoutListener {
//            val transfer = TransferPosition(
//                Point(990, 1556),
//                Point(binding.background.width, binding.background.height)
//            )
//            vm.setHomeArea(
//                listOf(
//                    HomeArea(
//                        true,
//                        0,
//                        listOf(
//                            transfer.toViewPoint(Point(0, 1270)),
//                            transfer.toViewPoint(Point(0, 1556)),
//                            transfer.toViewPoint(Point(990, 1556)),
//                            transfer.toViewPoint(Point(980, 980)),
//                            transfer.toViewPoint(Point(429, 630)),
//                        )
//                    ),
//                    HomeArea(
//                        true,
//                        1,
//                        listOf(
//                            transfer.toViewPoint(Point(390, 655)),
//                            transfer.toViewPoint(Point(621, 767)),
//                            transfer.toViewPoint(Point(913, 711)),
//                            transfer.toViewPoint(Point(645, 524)),
//                        )
//                    )
//                )
//            )
//
//        }
    }
}