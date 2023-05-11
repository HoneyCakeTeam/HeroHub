package com.example.herohub.ui.series

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSeriesBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.EventObserve

class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_series
    override val viewModel: SeriesViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        observeSeriesClick()
    }

    private fun observeSeriesClick() {
        viewModel.seriesClick.observe(this, EventObserve { seriesId ->
            navigateToSeriesDetails(seriesId)
        })
    }

    private fun navigateToSeriesDetails(seriesId: Int) {
        val action = SeriesFragmentDirections
            .actionSeriesFragmentToSeriesDetailsFragment(seriesId)
        findNavController().navigate(action)
    }

    private fun initiateAdapter() {
        val adapter = SeriesAdapter(viewModel)
        binding.recyclerSeries.adapter = adapter
    }

}