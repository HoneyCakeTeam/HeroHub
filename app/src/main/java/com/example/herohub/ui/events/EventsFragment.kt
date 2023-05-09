package com.example.herohub.ui.events

import androidx.fragment.app.viewModels
import com.example.herohub.R
import com.example.herohub.databinding.FragmentEventsBinding
import com.example.herohub.ui.base.BaseFragment

class EventsFragment : BaseFragment<FragmentEventsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_events
    override val viewModel: EventsViewModel by viewModels()

    override fun setup() {
        initiateAdapter()
//        navigationToEventDetails()
    }

//    private fun navigationToEventDetails() {
//        viewModel.eventId.observe(this) { eventId ->
//            val action = CategoriesFragmentDirections
//                .actionCategoriesFragmentToEventsDetailsFragment(eventId)
//            Navigation.findNavController(binding.root).navigate(action)
//        }
//    }

    private fun initiateAdapter() {
        val adapter = EventsAdapter(viewModel)
        binding.recyclerEvents.adapter = adapter
    }

}