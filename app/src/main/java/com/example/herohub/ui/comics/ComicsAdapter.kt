package com.example.herohub.ui.comics

import com.example.herohub.R
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class ComicsAdapter(listener: ComicInteractionListener) : BaseAdapter<Comic>(listener) {
    override val layoutId: Int = R.layout.item_category
}

interface ComicInteractionListener : BaseInteractionListener {
    fun onClickComic(id: Int)
}