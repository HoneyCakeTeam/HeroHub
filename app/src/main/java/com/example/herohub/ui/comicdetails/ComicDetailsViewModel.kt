package com.example.herohub.ui.comicdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val marvelRepositoryImp:MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    private val comicArgs = ComicDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _comicsResponse = MutableLiveData<UiState<List<Comic>>>()
    val comicsResponse: LiveData<UiState<List<Comic>>>
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
            marvelRepositoryImp.getComicDetails(comicArgs.comicId),
            ::onGetComicSuccess,
            ::onGetComicFailure
        )
    }

    private fun onGetComicSuccess(state: UiState<List<Comic>>) {
        _comicsResponse.value = state
        state.toData()?.let {
            _comic.value = it.get(0)
        }
        _isFavorite.value = marvelRepositoryImp.isFavorite(comic.value?.id.toString())
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
            marvelRepositoryImp.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepositoryImp.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }
}