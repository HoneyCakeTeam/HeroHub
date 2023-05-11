package com.example.herohub.ui.comicdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.Repository
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class ComicDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName
    private val repository = Repository()
    private val comicArgs = ComicDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _comicsResponse = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comicsResponse: LiveData<UiState<DataResponse<Comic>>>
        get() = _comicsResponse

    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic>
        get() = _comic

    init {
        getComic()
    }

    private fun getComic() {
        disposeSingle(
            repository.getComic(comicArgs.comicId),
            ::onGetComicSuccess,
            ::onGetComicFailure
        )
    }

    private fun onGetComicSuccess(state: UiState<DataResponse<Comic>>) {
        _comicsResponse.postValue(state)
        state.toData()?.let {
            _comic.postValue(it.results?.get(0))
        }
    }

    private fun onGetComicFailure(throwable: Throwable) {
        _comicsResponse.postValue(UiState.Error(throwable.message.toString()))
    }
}