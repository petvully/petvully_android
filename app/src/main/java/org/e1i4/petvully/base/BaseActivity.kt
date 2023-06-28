package org.e1i4.petvully.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class BaseActivity<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
) : AppCompatActivity(), View.OnClickListener {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    /******** onCreate 사용시 super call 필수 ********/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
    }

    protected fun setOnClickListeners(vararg views: View) {
        try {
            views.forEach { view ->
                view.setOnClickListener(this)
            }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch {
                launch {
                    _binding = bindingInflater(layoutInflater)
                }.join()
                launch {
                    setOnClickListeners(*views)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
    }

    protected inline fun <reified T : AppCompatActivity> gotoActivityWithClear() {
        val intent = Intent(this, T::class.java)
        overridePendingTransition(0, 0)
        startActivity(intent)
        finish()
    }
}