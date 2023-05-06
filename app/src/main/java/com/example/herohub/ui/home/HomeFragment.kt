package com.example.herohub.ui.home

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentHomeBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.home.adapter.HomeAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val TAG: String = this::class.java.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private val homeItems = mutableListOf<HomeItem>()

    override fun setup() {
        initHomeAdapter()
        observeLiveData()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(homeItems, viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun observeLiveData() {
        observeSeries()
        observeSuperHeroesResponse()
        observeMostPopularComicsResponse()
    }

    private fun observeSeries() {
        viewModel.seriesResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { series ->
                if (!series.isNullOrEmpty()) {
                    homeItems.add(HomeItem.Slider(series))
                    homeAdapter.setItems(homeItems)
                }
            }
        }
    }

    private fun observeSuperHeroesResponse() {
        viewModel.characterResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { character ->
                if (!character.isNullOrEmpty()) {
                    homeItems.add(HomeItem.SuperHeroes(character))
                    homeAdapter.setItems(homeItems)
                }
            }
        }
    }

    private fun observeMostPopularComicsResponse() {
        viewModel.mostPopularComicsResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { comic ->
                if (!comic.isNullOrEmpty()) {
                    homeItems.add(HomeItem.MostPopularComics(comic))
                    homeAdapter.setItems(homeItems)
                }
            }
        }
    }
}