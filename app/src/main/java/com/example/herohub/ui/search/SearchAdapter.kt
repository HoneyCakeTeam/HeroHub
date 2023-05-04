package com.example.herohub.ui.search


import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class SearchAdapter<T>(listener: SearchInteractionListener) :
    BaseAdapter<T>(listener) {
    override val layoutId: Int = R.layout.item_search
}

interface SearchInteractionListener : BaseInteractionListener {
    fun <T> onItemClick(item: T)
}