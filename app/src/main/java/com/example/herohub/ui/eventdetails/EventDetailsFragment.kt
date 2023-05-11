package com.example.herohub.ui.eventdetails

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentEventsDetailsBinding
import com.example.herohub.ui.base.BaseFragment

class EventDetailsFragment : BaseFragment<FragmentEventsDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val viewModel: EventDetailsViewModel by viewModels()
    override val layoutIdFragment = R.layout.fragment_events_details
}