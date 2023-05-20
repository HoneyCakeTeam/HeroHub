package com.example.herohub.ui.seriesdetails

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSeriesDetailsBinding
import com.example.herohub.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_series_details
    override val viewModel: SeriesDetailsViewModel by viewModels()
}