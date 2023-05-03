package com.example.herohub.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    abstract val TAG: String
    abstract val bindingInflater: (LayoutInflater) -> VB
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract fun getViewModel(): Class<VM>
    open lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModel()]
        setup()
    }

    abstract fun setup()

    protected fun log(value: Any) {
        Log.e(TAG, value.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}