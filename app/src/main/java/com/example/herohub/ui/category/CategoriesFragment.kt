package com.example.herohub.ui.category

import android.view.LayoutInflater
import androidx.navigation.Navigation
import com.example.herohub.R
import com.example.herohub.data.local.model.Category
import com.example.herohub.databinding.FragmentCategoriesBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.navigateTo

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoryViewModel>() {
    override val TAG: String = this::class.java.simpleName
    override val bindingInflater: (LayoutInflater) -> FragmentCategoriesBinding
        get() = FragmentCategoriesBinding::inflate

    override fun getViewModel() = CategoryViewModel::class.java


    override fun setup() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initCategoryAdapter()
//        navigateToCategory()

    }

//    private fun navigateToCategory() {
//        viewModel.itemCategory.observe(this) { category ->
//            when (category.categoryName) {
//                getString(R.string.characters) -> Navigation.navigateTo(
//                    binding.root,
//                    R.id.action_categories_fragment_to_characterFragment
//                )
//
//                getString(R.string.series) -> Navigation.navigateTo(
//                    binding.root,
//                    R.id.action_categories_fragment_to_seriesFragment
//                )
//
//                getString(R.string.comics) -> Navigation.navigateTo(
//                    binding.root,
//                    R.id.action_categories_fragment_to_comicsFragment
//                )
//
//                getString(R.string.events) -> Navigation.navigateTo(
//                    binding.root,
//                    R.id.action_categories_fragment_to_eventsFragment
//                )
//
//                getString(R.string.creators) -> Navigation.navigateTo(
//                    binding.root,
//                    R.id.action_categories_fragment_to_creatorFragment
//                )
//
//                getString(R.string.stories) -> Navigation.navigateTo(
//                    binding.root,
//                    R.id.action_categories_fragment_to_storyFragment
//                )
//            }
//
//        }
//    }

    private fun initCategoryAdapter() {
        val adapter = CategoryAdapter(emptyList(), viewModel)
        binding.categoriesRecycler.adapter = adapter
    }

}