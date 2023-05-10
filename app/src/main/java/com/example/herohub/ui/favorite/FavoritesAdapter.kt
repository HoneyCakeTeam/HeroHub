package com.example.herohub.ui.favorite

import com.example.herohub.R
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.FavoriteItem
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class FavoritesAdapter(listener: FavoriteInteractionListener) : BaseAdapter<FavoriteItem>(listener) {
    override val layoutId: Int = R.layout.item_favorite
}

interface FavoriteInteractionListener : BaseInteractionListener {
    fun onClickFavorite(id: String)
    fun onClickFavoriteIcon(favoriteItem: FavoriteItem)
}