package com.example.herohub.ui.series

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSeriesBinding
import com.example.herohub.ui.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_series
    override val viewModel: SeriesViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        navigateToSeriesDetails()
    }

    private fun navigateToSeriesDetails() {
        viewModel.clickSeries.observe(this) { event ->
            event.getContentIfNotHandled()?.let { seriesId ->
                val action = SeriesFragmentDirections
                    .actionSeriesFragmentToSeriesDetailsFragment(seriesId)
                findNavController().navigate(action)
            }
        }
    }

    private fun initiateAdapter() {
        val adapter = SeriesAdapter(viewModel)
        binding.recyclerSeries.adapter = adapter
    }

}