package com.example.herohub.ui.comicdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class ComicDetailsViewModel : BaseViewModel() {
    override val TAG = "ComicDetailsViewModel"
    private val repository = Repository()
    private val _comics = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comics: LiveData<UiState<DataResponse<Comic>>>
        get() = _comics

    fun getComic(comicId: Int) {
        _comics.postValue(UiState.Loading)
        disposeObservable(
            repository.getComic(comicId),
            ::onGetComicSuccess,
            ::onGetComicFailure
        )
    }

    private fun onGetComicSuccess(UiState: UiState<DataResponse<Comic>>) {
        _comics.postValue(UiState)
    }

    private fun onGetComicFailure(throwable: Throwable) {
        _comics.postValue(UiState.Error(throwable.message.toString()))
    }
}