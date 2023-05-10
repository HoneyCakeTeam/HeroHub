package com.example.herohub.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        collectEvent()
    }

    private fun initHomeAdapter() {
        homeAdapter = HomeAdapter(homeItems, viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun collectEvent(){
        viewModel.homeUiEvent.observe(this){ event ->
            event.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: HomeUiEvent) {
        val action = when (event) {
            is HomeUiEvent.ClickCharacterEvent -> {
                HomeFragmentDirections.actionHomeFragmentToCharactersDetailsFragment2(event.id)
            }

            is HomeUiEvent.ClickComicEvent -> {
                HomeFragmentDirections.actionHomeFragmentToComicsDetailsFragment(event.id)
            }

            is HomeUiEvent.ClickEventEvent -> {
                HomeFragmentDirections.actionHomeFragmentToEventsDetailsFragment(event.id)
            }

            is HomeUiEvent.ClickSeriesEvent -> {
                HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(event.id)
            }

            HomeUiEvent.ClickSeeAllCharactersEvent -> {
                HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
            }

            HomeUiEvent.ClickSeeAllComicsEvent -> {
                HomeFragmentDirections.actionHomeFragmentToComicsFragment()
            }

            HomeUiEvent.ClickSeeAllEventsEvent -> {
                HomeFragmentDirections.actionHomeFragmentToEventsFragment()
            }

            HomeUiEvent.ClickSeeAllSeriesEvent -> {
                HomeFragmentDirections.actionHomeFragmentToSeriesFragment()
            }
        }
        findNavController().navigate(action)
    }


//    override fun onResume() {
//        super.onResume()
//        if (viewModel.stateInitialized()) {
//            binding.recyclerViewHome.layoutManager?.onRestoreInstanceState(
//                viewModel.restoreRecyclerViewState()
//            )
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        binding.recyclerViewHome.layoutManager?.onSaveInstanceState()
//            ?.let { viewModel.saveRecyclerViewState(it) }
//    }
}