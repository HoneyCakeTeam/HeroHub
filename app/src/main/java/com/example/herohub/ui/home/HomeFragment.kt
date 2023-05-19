package com.example.herohub.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentHomeBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.home.adapter.HomeAdapter
import com.example.herohub.ui.utils.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String = this::class.java.simpleName.toString()
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private val homeItems = mutableListOf<HomeItem>()

    override fun setup() {
        initHomeAdapter()
        collectEvent()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(homeItems, viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun collectEvent() {
        viewModel.homeUIEvent.observe(this, EventObserve {
            if (it != null) {
                onEvent(it)
            }
        })
    }

    private fun onEvent(event: HomeUiEvent) {
        val action = when (event) {
            is HomeUiEvent.ClickSeeAllCharacterEvent -> {
                HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
            }

            is HomeUiEvent.ClickEventEvent -> {
                HomeFragmentDirections.actionHomeFragmentToEventsDetailsFragment(event.id)
            }

            HomeUiEvent.ClickSeeAllEventsEvent -> {
                HomeFragmentDirections.actionHomeFragmentToEventsFragment()
            }

            is HomeUiEvent.ClickComicEvent -> {
                HomeFragmentDirections.actionHomeFragmentToComicsDetailsFragment(event.id)
            }
            HomeUiEvent.ClickSeeAllComicsEvent -> {
                HomeFragmentDirections.actionHomeFragmentToComicsFragment()
            }

            is HomeUiEvent.ClickCharacterEvent -> {
                HomeFragmentDirections.actionHomeFragmentToCharactersDetailsFragment(event.characterId)
            }

            is HomeUiEvent.ClickSeeAllSeriesEvent -> {
                HomeFragmentDirections.actionHomeFragmentToSeriesFragment()
            }

            is HomeUiEvent.ClickMostPopularSeriesItem -> {
                HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(event.id)
            }

            is HomeUiEvent.ClickSliderItemEvent -> {
                HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(event.id)
            }
        }
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.stateInitialized()) {
            binding.recyclerViewHome.layoutManager?.onRestoreInstanceState(
                viewModel.restoreRecyclerViewState()
            )
        }
    }

    override fun onPause() {
        super.onPause()
        binding.recyclerViewHome.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.saveRecyclerViewState(it) }
    }
}