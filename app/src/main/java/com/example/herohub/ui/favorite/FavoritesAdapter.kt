package com.example.herohub.ui.favorite

import com.example.herohub.R
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener
import com.example.herohub.ui.category.CategoryInteractionListener

class FavoritesAdapter(listener: FavoriteInteractionListener) : BaseAdapter<Comic>(listener) {
    override val layoutId: Int = R.layout.item_favorite
}

interface FavoriteInteractionListener : BaseInteractionListener {
    fun onClickFavorite(id: Int)
}