package com.example.herohub.ui.category

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCategoriesBinding
import com.example.herohub.ui.base.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_categories
    override val viewModel: CategoryViewModel by viewModels()
    override fun setup() {
        initiateCategoryViewPager()
    }

    private fun initiateCategoryViewPager() {

    }

}