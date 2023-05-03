package com.example.herohub.ui.category

import android.view.LayoutInflater
import androidx.navigation.Navigation
import com.example.herohub.R
import com.example.herohub.data.local.model.Category
import com.example.herohub.databinding.FragmentCategoriesBinding
import com.example.herohub.ui.base.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoryViewModel>() {
    override val TAG: String = this::class.java.simpleName
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding
        get() = FragmentCategoriesBinding::inflate

    override fun getViewModel() = CategoryViewModel::class.java


    override fun setup() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initCategoryAdapter()
        viewModel.itemCategory.observe(this) { category ->
            when (category.categoryName) {
                "Characters" -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_categories_fragment_to_characterFragment)
                }
            }

        }
    }

    private fun initCategoryAdapter() {
        val adapter = CategoryAdapter(emptyList(), viewModel)
        binding.categoriesRecycler.adapter = adapter
    }


}