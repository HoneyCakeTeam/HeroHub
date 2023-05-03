package com.example.herohub.ui.search

import com.example.herohub.BaseAdapter
import com.example.herohub.BaseInteractionListener
import com.example.herohub.R

class SearchAdapter<T>(listener: SearchInteractionListener) :
    BaseAdapter<T>(listener) {
    override val layoutId: Int = R.layout.item_search
}

interface SearchInteractionListener : BaseInteractionListener {
    fun <T> onItemClick(item: T)
}