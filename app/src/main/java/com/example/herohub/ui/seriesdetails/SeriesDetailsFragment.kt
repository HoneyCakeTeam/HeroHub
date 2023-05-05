package com.example.herohub.ui.seriesdetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSeriesDetailsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.characterdetails.CharacterDetailsFragmentArgs

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_series_details
    override val viewModel: SeriesDetailsViewModel by viewModels()
   // private val args: SeriesDetailsFragmentArgs by navArgs()

    override fun setup(){
        viewModel.getSeriesDetails(120)
    }




}