package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class PopularSeriesAdapter(listener:PopularSeriesInteractionListener):BaseAdapter<Series>(listener) {
    override val layoutId: Int = R.layout.item_popular_series_slider
}

interface PopularSeriesInteractionListener : BaseInteractionListener {
    fun onItemClick(id: Int)
}