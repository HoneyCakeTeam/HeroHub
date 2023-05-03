package com.example.herohub.ui.category

import android.view.LayoutInflater
import com.example.herohub.databinding.FragmentCategoriesBinding
import com.example.herohub.ui.base.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding , CategoryViewModel >() {
    override val TAG: String = this::class.java.simpleName
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding
        get() = FragmentCategoriesBinding::inflate

    override fun getViewModel() = CategoryViewModel::class.java


    override fun setup() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

}