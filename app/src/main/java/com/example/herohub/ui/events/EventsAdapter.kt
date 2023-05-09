package com.example.herohub.ui.events

import com.example.herohub.R
import com.example.herohub.model.Event
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class EventsAdapter(listener: EventsInteractionListener) : BaseAdapter<Event>(listener) {
    override val layoutId: Int = R.layout.item_event
}

interface EventsInteractionListener : BaseInteractionListener {
    fun onClickEvent(id: Int)
}