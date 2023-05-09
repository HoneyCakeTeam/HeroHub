package com.example.herohub.ui.series

import com.example.herohub.R
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class SeriesAdapter(listener: SeriesInteractionListener) : BaseAdapter<Series>(listener) {
    override val layoutId: Int = R.layout.item_series
}

interface SeriesInteractionListener : BaseInteractionListener {
    fun onClickSeries(id: Int)
}