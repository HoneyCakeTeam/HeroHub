package com.example.herohub.ui.characterdetails

import com.example.herohub.R
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener


class ComicsAdapter(
    listener: ComicsInteractionListener,
) : BaseAdapter<Comic>(listener) {
    override val layoutId: Int = R.layout.comic_item

}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComic(id: Int)
}