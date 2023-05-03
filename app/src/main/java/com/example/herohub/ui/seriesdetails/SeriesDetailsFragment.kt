package com.example.herohub.ui.seriesdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.data.Repository
import com.example.herohub.databinding.FragmentSeriesDetailsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


class SeriesDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSeriesDetailsBinding
    private val viewModel: SeriesDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_series_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this
        viewModel.getAllSerieslist()
        val data=
            viewModel.data.observe(viewLifecycleOwner) {
                binding.data.text = it.toData()?.characters.toString()
            }
        binding.data.text= data.toString()

    }



}