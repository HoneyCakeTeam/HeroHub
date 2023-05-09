package com.example.herohub.ui

import com.example.herohub.ui.base.BaseInteractionListener

interface CategoryInteractionListener: BaseInteractionListener {
    fun onClickItem(id: Int)
}