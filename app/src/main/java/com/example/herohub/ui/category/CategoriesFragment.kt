package com.example.herohub.ui.category

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentCategoriesBinding
import com.example.herohub.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_categories
    override val viewModel: CategoryViewModel by viewModels()
    private val charactersFragment by lazy { CharactersFragment() }
    private val comicsFragment by lazy { ComicsFragment() }
    private val eventsFragment by lazy { EventsFragment() }
    private val seriesFragment by lazy { SeriesFragment() }
    private val storiesFragment by lazy { StoriesFragment() }
    private val categories = listOf(
        charactersFragment,
        comicsFragment,
        eventsFragment,
        seriesFragment,
//        storiesFragment
    )
    private val categoriesTitles = listOf(
        "Characters",
        "Comics",
        "Events",
        "Series",
    )

    override fun setup() {
        initiateCategoryViewPager()
        initiateTabLayout()
    }

    private fun initiateTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPagerCategory)
        { tab, position ->
            tab.text = categoriesTitles[position]
        }.attach()
    }

    private fun initiateCategoryViewPager() {
        val pagerAdapter = CategoryViewPagerAdapter(this, categories)
        binding.viewPagerCategory.adapter = pagerAdapter
    }

}