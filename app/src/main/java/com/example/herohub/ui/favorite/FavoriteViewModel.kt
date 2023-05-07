package com.example.herohub.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.model.Event
import com.example.herohub.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel

class FavoriteViewModel : BaseViewModel() {

    override val TAG = "Favorite_VIEW_MODEL"
    private val _favorites = MutableLiveData<MutableList<FavoriteItem>>()
    val favorites: MutableLiveData<MutableList<FavoriteItem>>
        get() = _favorites

}


