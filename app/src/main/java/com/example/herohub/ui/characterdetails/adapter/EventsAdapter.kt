package com.example.herohub.ui.characterdetails.adapter

import com.example.herohub.R
import com.example.herohub.data.domain.model.Event
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

/**
 * Created by Aziza Helmy on 5/11/2023.
 */
class EventsAdapter(listener: EventsInteractionListener) : BaseAdapter<Event>(listener) {
    override val layoutId: Int = R.layout.item_character_events
}

interface EventsInteractionListener : BaseInteractionListener {
    fun onClickEvent(id: Int)
}