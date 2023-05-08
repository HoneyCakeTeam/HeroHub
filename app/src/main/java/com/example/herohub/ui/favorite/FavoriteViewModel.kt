package com.example.herohub.ui.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel

class FavoriteViewModel : BaseViewModel(), FavoriteInteractionListener {

    private val repository = Repository()
    override val TAG = this::class.java.simpleName
    private val _favorites = MutableLiveData<List<FavoriteItem>>()
    val favorites: MutableLiveData<List<FavoriteItem>>
        get() = _favorites

    fun addFavorite() {
        repository.addToFavorite(
            FavoriteItem(
                "1011334",
                "3-D Man",
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
            )
        )
    }

    fun initShared(context: Context) {
        repository.initShared(context)
    }

    fun retrieveFavorites() {
        _favorites.value = repository.getFavorites()
        log(favorites.value.toString())
    }

    override fun onClickFavorite(id: Int) {

    }

}


