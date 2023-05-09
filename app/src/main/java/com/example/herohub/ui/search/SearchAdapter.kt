package com.example.herohub.ui.search


import com.example.herohub.R
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class SearchAdapter(listener: SearchInteractionListener) :
    BaseAdapter<Character>(listener) {
    override val layoutId: Int = R.layout.item_search
}

interface SearchInteractionListener : BaseInteractionListener {
    fun <T> onClickItem(item: T)
}