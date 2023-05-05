package com.example.herohub.ui.category.events

import com.example.herohub.R
import com.example.herohub.model.Event
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseCategoryInteractionListener

class EventsAdapter(listener: EventsInteractionListener) : BaseAdapter<Event>(listener) {
    override val layoutId: Int = R.layout.item_category
}

interface EventsInteractionListener : BaseCategoryInteractionListener