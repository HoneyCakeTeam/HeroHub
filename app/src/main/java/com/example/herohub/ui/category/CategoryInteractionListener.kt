package com.example.herohub.ui.category

import com.example.herohub.ui.base.BaseInteractionListener

interface CategoryInteractionListener: BaseInteractionListener {
    fun onClickItem(id: Int)
}