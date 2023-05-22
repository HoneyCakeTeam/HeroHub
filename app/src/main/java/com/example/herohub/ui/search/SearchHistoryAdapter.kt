package com.example.herohub.ui.search


import com.example.herohub.R
import com.example.herohub.domain.model.SearchHistory
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class SearchHistoryAdapter(listener: SearchHistoryInteractionListener) :
    BaseAdapter<SearchHistory>(listener) {
    override val layoutId: Int = R.layout.item_search_history
}

interface SearchHistoryInteractionListener : BaseInteractionListener {
    fun <T> onClickItemHistory(item: T)
}