package com.example.herohub.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.data.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler

class FavoriteViewModel : BaseViewModel(), FavoriteInteractionListener {

    private val repository = Repository()
    override val TAG: String = this::class.java.simpleName
    private val _favorites = MutableLiveData<List<FavoriteItem>>()
    val favorites: LiveData<List<FavoriteItem>>
        get() = _favorites

    private val _favoriteClick = MutableLiveData<EventHandler<FavoriteItem>>()
    val favoriteClick: LiveData<EventHandler<FavoriteItem>>
        get() = _favoriteClick

    fun retrieveFavorites() {
        _favorites.postValue(repository.getFavorites())
    }

    override fun onClickFavorite(favoriteItem: FavoriteItem) {
        _favoriteClick.postValue(EventHandler(favoriteItem))
    }

    override fun onClickFavoriteIcon(favoriteItem: FavoriteItem) {
        repository.removeFavorite(favoriteItem)
        retrieveFavorites()
    }

}


