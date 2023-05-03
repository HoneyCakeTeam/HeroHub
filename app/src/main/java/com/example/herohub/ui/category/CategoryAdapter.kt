package com.example.herohub.ui.category

import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener
import com.example.herohub.R
import com.example.herohub.data.local.model.Category

class CategoryAdapter(categories: List<Category>, listener: CategoryInteractionListener) :
    BaseAdapter<Category>(categories, listener) {
    override val layoutId: Int = R.layout.item_category
}

interface CategoryInteractionListener : BaseInteractionListener {
    fun onCategoryClick(item: Category)
}