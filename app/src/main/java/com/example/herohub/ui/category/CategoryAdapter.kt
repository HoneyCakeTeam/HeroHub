package com.example.herohub.ui.category

import com.example.herohub.R
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class CategoryAdapter(category: List<Character>, listener: CategoryInteractionListener) :
    BaseAdapter<Character>(category, listener) {
    override val layoutId: Int = R.layout.item_category
}

interface CategoryInteractionListener : BaseInteractionListener {
    fun onItemClick(id: Int)
}