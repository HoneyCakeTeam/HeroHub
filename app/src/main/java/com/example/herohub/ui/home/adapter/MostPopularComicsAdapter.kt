package com.example.herohub.ui.home.adapter

import com.example.herohub.R
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class MostPopularComicsAdapter(listener: MostPopularComicsInteractionListener) :
    BaseAdapter<Comic>(listener) {
    override val layoutId: Int = R.layout.item_most_popular_comics
}

interface MostPopularComicsInteractionListener : BaseInteractionListener {
    fun onMostPopularComicsItemClick(id: Int)

}
