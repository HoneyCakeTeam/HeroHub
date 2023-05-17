package com.example.herohub.ui.characterdetails.adapter

import com.example.herohub.R
import com.example.herohub.domin.model.Comic
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener


class ComicsAdapter(
    listener: ComicsInteractionListener,
) : BaseAdapter<Comic>(listener) {
    override val layoutId: Int = R.layout.item_character_comics

}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComic(id: Int)
}