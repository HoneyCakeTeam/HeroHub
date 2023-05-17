package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.data.domain.model.Event
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class SliderAdapter(listener: SliderInteractionListener) :
    BaseAdapter<Event>(listener) {

    override val layoutId: Int = R.layout.item_slider
}

interface SliderInteractionListener : BaseInteractionListener {
    fun onSliderItemClick(id: Int)
}