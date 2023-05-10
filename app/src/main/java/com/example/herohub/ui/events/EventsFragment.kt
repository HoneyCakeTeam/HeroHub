package com.example.herohub.ui.events

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.herohub.R
import com.example.herohub.databinding.FragmentEventsBinding
import com.example.herohub.ui.base.BaseFragment
import com.example.herohub.utills.EventObserve

class EventsFragment : BaseFragment<FragmentEventsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_events
    override val viewModel: EventsViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
        observeToEvent()
    }

//    private fun navigationToEventDetails() {
//        viewModel.clickEvent.observe(this) { event ->
//            event.getContentIfNotHandled()?.let { eventId ->
//                val action = EventsFragmentDirections
//                    .actionEventsFragmentToEventsDetailsFragment(eventId)
//                findNavController().navigate(action)
//            }
//        }
//    }

    private fun observeToEvent() {
        viewModel.clickEvent.observe(this, EventObserve { eventId ->
            navigationToEventDetails(eventId)
        })
    }
    private fun navigationToEventDetails(eventId: Int) {
        val action = EventsFragmentDirections.actionEventsFragmentToEventsDetailsFragment(eventId)
        findNavController().navigate(action)
    }

    private fun initiateAdapter() {
        val adapter = EventsAdapter(viewModel)
        binding.recyclerEvents.adapter = adapter
    }

}