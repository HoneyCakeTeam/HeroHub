package com.example.herohub.ui.category.comics

import com.example.herohub.R
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseCategoryInteractionListener

class ComicsAdapter(listener: ComicInteractionListener) : BaseAdapter<Comic>(listener) {
    override val layoutId: Int = R.layout.item_category
}

interface ComicInteractionListener : BaseCategoryInteractionListener