package org.e1i4.petvully.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class BaseFragment<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
) : Fragment(), View.OnClickListener {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun bindingIsNotNull() = _binding != null


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

}