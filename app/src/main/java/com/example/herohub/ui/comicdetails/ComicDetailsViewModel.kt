package com.example.herohub.ui.comicdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.data.repository.MarvelRepositoryImpl
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState

class ComicDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName
    private val marvelRepositoryImpl = MarvelRepositoryImpl()
    private val comicArgs = ComicDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _comicsResponse = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comicsResponse: LiveData<UiState<DataResponse<Comic>>>
        get() = _comicsResponse

    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic>
        get() = _comic

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    init {
        getComic()
    }

    private fun getComic() {
        disposeSingle(
            marvelRepositoryImpl.getComic(comicArgs.comicId),
            ::onGetComicSuccess,
            ::onGetComicFailure
        )
    }

    private fun onGetComicSuccess(state: UiState<DataResponse<Comic>>) {
        _comicsResponse.value = state
        state.toData()?.let {
            _comic.value = it.results?.get(0)
        }
        _isFavorite.value = marvelRepositoryImpl.isFavorite(comic.value?.id.toString())
    }

    private fun onGetComicFailure(throwable: Throwable) {
        _comicsResponse.postValue(UiState.Error(throwable.message.toString()))
    }

    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            _comic.value?.id.toString(),
            _comic.value?.title.toString(),
            _comic.value?.imageUrl.toString(),
            FavoriteItem.Type.COMIC
        )

        if (isFavorite.value == true) {
            marvelRepositoryImpl.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepositoryImpl.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }
}