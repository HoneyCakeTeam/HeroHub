package com.example.herohub.ui.home

import androidx.fragment.app.viewModels
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
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
    private val imageList = mutableListOf<SlideModel>()

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
        observeSliderSeries()
        observeSuperHeroesResponse()
        observeMostPopularCharactersResponse()
    }

    private fun observeSliderSeries() {
        viewModel.seriesResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results?.let { series ->
                if (series.isNotEmpty()) {
                    val images = series.filterNot {
                        it.thumbnail?.path?.contains("image_not_available")!!
                    }.shuffled().take(5).map {
                        SlideModel(
                            "${it.thumbnail?.path}.jpg",
                            it.title ?: "",
                            ScaleTypes.FIT
                        )
                    }
                    imageList.addAll(images)
                    binding.imageSliderImage.setImageList(imageList)
                }
            }
        }
    }

    private fun observeSeries() {
        viewModel.seriesResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { series ->
                if (!series.isNullOrEmpty()) {
                    homeItems.add(HomeItem.Slider(series.filterNot {
                        it.thumbnail?.path?.contains("image_not_available")!!
                    }.shuffled().take(15)))
                    homeAdapter.setItems(homeItems)
                }
            }
        }
    }

    private fun observeSuperHeroesResponse() {
        viewModel.characterResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { character ->
                if (!character.isNullOrEmpty()) {
                    homeItems.add(HomeItem.SuperHeroes(character.filterNot {
                        it.thumbnail?.path?.contains("image_not_available")!!
                    }.take(15)))
                    homeAdapter.setItems(homeItems)
                }
            }
        }
    }

    private fun observeMostPopularCharactersResponse() {
        viewModel.characterResponse.observe(viewLifecycleOwner) { uiState ->
            uiState.toData()?.results.let { character ->
                if (!character.isNullOrEmpty()) {
                    homeItems.add(HomeItem.MostPopularCharacters(character.filterNot {
                        it.thumbnail?.path?.contains("image_not_available")!!
                    }.filter {
                        it.run {
                            (comics?.available!! +
                                    series?.available!! +
                                    events?.available!! +
                                    stories?.available!!) > 100
                        }
                    }.shuffled().take(20)))
                    homeAdapter.setItems(homeItems)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        imageList.clear()
        homeItems.clear()
    }
}