package com.example.herohub.ui.home

import android.view.LayoutInflater
import com.example.herohub.databinding.FragmentHomeBinding
import com.example.herohub.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val TAG: String = this::class.java.simpleName.toString()
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun setup() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}