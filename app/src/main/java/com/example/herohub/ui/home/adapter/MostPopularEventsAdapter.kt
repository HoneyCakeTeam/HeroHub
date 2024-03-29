package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.domain.model.Event
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class MostPopularEventsAdapter(listener: MostPopularEventsInteractionListener)
    : BaseAdapter<Event>(listener){

    override val layoutId: Int = R.layout.item_most_popular_event
}

interface MostPopularEventsInteractionListener : BaseInteractionListener {

    fun onMostPopularEventClick(id : Int)
    fun onClickSeeAllEvents()
}