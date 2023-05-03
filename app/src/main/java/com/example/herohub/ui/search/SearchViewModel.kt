package com.example.herohub.ui.search

import com.example.herohub.data.Repository
import com.example.herohub.ui.base.BaseViewModel

class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()
    override fun <T> onItemClick(item: T) {
        TODO("Not yet implemented")
    }


}