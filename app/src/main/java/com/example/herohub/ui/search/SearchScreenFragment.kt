package com.example.herohub.ui.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.herohub.R
import com.example.herohub.databinding.FragmentSearchScreenBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.eventdetails.EventDetailsViewModel

class SearchScreenFragment  : BaseFragment<FragmentSearchScreenBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_search_screen
    override val viewModel: SearchViewModel by viewModels()

    override fun setup() {

    }

}