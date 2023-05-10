package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class MostPopularSeriesAdapter(listener: MostPopularSeriesInteractionListener) :
    BaseAdapter<Series>(listener) {

    override val layoutId: Int = R.layout.item_most_popular_series
}

interface MostPopularSeriesInteractionListener : BaseInteractionListener {

    fun onMostPopularSeriesItemClick(id: Int)
    fun onClickSeeAllSeries()

}