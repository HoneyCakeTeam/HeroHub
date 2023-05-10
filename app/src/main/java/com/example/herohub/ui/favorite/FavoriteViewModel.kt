package com.example.herohub.ui.favorite

import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel

class FavoriteViewModel : BaseViewModel(), FavoriteInteractionListener {

    private val repository = Repository()
    override val TAG: String = this::class.java.simpleName
    private val _favorites = MutableLiveData<List<FavoriteItem>>()
    val isListChanged = MutableLiveData<Boolean>()
    val favorites: MutableLiveData<List<FavoriteItem>>
        get() = _favorites

    init {
        addFavorite()
        retrieveFavorites()
        isListChanged.postValue(true)
    }

    private fun addFavorite() {
        repository.addToFavorite(
            FavoriteItem(
                "1011334",
                "3-D Man",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
            )
        )
    }

    private fun retrieveFavorites() {
        _favorites.postValue(repository.getFavorites())
    }

    override fun onClickFavorite(id: String) {
        //to do
    }

    fun resetIsListChanged() {
        isListChanged.postValue(false)
    }

    override fun onClickFavoriteIcon(favoriteItem: FavoriteItem) {
        repository.removeFavorite(favoriteItem)
        retrieveFavorites()
        isListChanged.postValue(true)
    }

}


