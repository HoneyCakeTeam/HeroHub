package com.example.herohub.ui.category

import com.example.herohub.R
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class CategoryAdapter(listener: CategoryInteractionListener) :
    BaseAdapter<String>(listener) {
    override val layoutId: Int = R.layout.item_category
}

interface CategoryInteractionListener : BaseInteractionListener {
    fun onItemClick(id: Int)
}