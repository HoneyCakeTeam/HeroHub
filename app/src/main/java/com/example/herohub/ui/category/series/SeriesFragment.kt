package com.example.herohub.ui.category.series

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSeriesBinding
import com.example.herohub.ui.base.BaseFragment


class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {

    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_series
    override val viewModel: SeriesViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
    }

    private fun initiateAdapter() {
        val adapter = SeriesAdapter(viewModel)


    }

}