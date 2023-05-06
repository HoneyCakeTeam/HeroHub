package com.example.herohub.ui.home

import android.util.Log
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
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun observeLiveData() {
        observeSeries()
        observeCharacterResponse()
        observeMostPopularComicsResponse()
    }

    private fun observeCharacterResponse() {
        viewModel.characterResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { character ->
                if (!character.isNullOrEmpty()) {
                    homeItems.add(HomeItem.SuperHeroes(character))
                    homeAdapter.setItems(homeItems)
                }
                Log.e("TAG", "homeItems characterResponse: $homeItems")
            }
        }
    }

    private fun observeMostPopularComicsResponse() {
        viewModel.mostPopularComicsResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { comics ->
                if (!comics.isNullOrEmpty()) {
                    homeItems.add(HomeItem.MostPopularComics(comics))
                    homeAdapter.setItems(homeItems)
                }

                Log.e("TAG", "homeItems mostPopularComicsResponse: $homeItems")
            }
        }
    }

    private fun observeSeries() {
        viewModel.seriesResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { series ->
                if (!series.isNullOrEmpty()) {
                    homeItems.add(HomeItem.Slider(series))
                    homeAdapter.setItems(homeItems)
                }
            }

            Log.e("TAG", "homeItems seriesResponse: $homeItems")
        }
    }
}