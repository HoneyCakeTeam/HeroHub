package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class PopularSeriesSliderAdapter(listener: PopularSeriesSliderInteractionListener) :
    BaseAdapter<Series>(listener) {

    override val layoutId: Int = R.layout.item_popular_series_slider
}

interface PopularSeriesSliderInteractionListener : BaseInteractionListener {
    fun onPopularSeriesSliderItemClick(id: Int)
}