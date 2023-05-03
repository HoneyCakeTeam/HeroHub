package com.example.herohub.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCategoriesBinding
import com.example.herohub.ui.base.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_categories
    override val viewModel: CategoryViewModel by viewModels()
    override fun setup() {
        initCategoryAdapter()
//        navigateToCategory()
        viewModel.itemCategory.observe(this){

        }

    }

//    private fun navigateToCategory() {
//        viewModel.itemCat.observe(this) { category ->
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