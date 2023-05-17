package com.example.herohub.ui.favorite

import com.example.herohub.R
import com.example.herohub.data.domain.model.FavoriteItem
import com.example.herohub.ui.base.BaseAdapter
import com.example.herohub.ui.base.BaseInteractionListener

class FavoritesAdapter(listener: FavoriteInteractionListener) : BaseAdapter<FavoriteItem>(listener) {
    override val layoutId: Int = R.layout.item_favorite
}

interface FavoriteInteractionListener : BaseInteractionListener {
    fun onClickFavorite(favoriteItem: FavoriteItem)
    fun onClickFavoriteIcon(favoriteItem: FavoriteItem)
}