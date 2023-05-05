package com.example.herohub.ui.category.series

import com.example.herohub.R
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseCategoryInteractionListener

class SeriesAdapter(listener: SeriesInteractionListener) : BaseAdapter<Series>(listener) {
    override val layoutId: Int
        get() = R.layout.item_category
}

interface SeriesInteractionListener : BaseCategoryInteractionListener