package com.example.herohub.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel

class FavoriteViewModel : BaseViewModel(), FavoriteInteractionListener {

    private val repository = Repository()
    override val TAG: String = this::class.java.simpleName
    private val _favorites = MutableLiveData<List<FavoriteItem>>()
    val favorites: LiveData<List<FavoriteItem>>
        get() = _favorites

    fun retrieveFavorites() {
        _favorites.postValue(repository.getFavorites())
    }

    override fun onClickFavorite(id: String) {
        //to do
    }

    override fun onClickFavoriteIcon(favoriteItem: FavoriteItem) {
        repository.removeFavorite(favoriteItem)
        retrieveFavorites()
    }

}


