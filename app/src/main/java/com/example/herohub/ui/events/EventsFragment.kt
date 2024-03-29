package com.example.herohub.ui.events

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentEventsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.ui.utils.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_events
    override val viewModel: EventsViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        observeEventClick()
    }

    private fun observeEventClick() {
        viewModel.eventClick.observe(this, EventObserve { eventId ->
            navigateToEventDetails(eventId)
        })
    }

    private fun navigateToEventDetails(eventId: Int) {
        val action = EventsFragmentDirections
            .actionEventsFragmentToEventsDetailsFragment(eventId)
        findNavController().navigate(action)
    }

    private fun initiateAdapter() {
        val adapter = EventsAdapter(viewModel)
        binding.recyclerEvents.adapter = adapter
    }

}