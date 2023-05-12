package com.example.herohub.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.data.model.Comic
import com.example.herohub.data.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.EventHandler
import com.example.herohub.utills.UiState

class ComicsViewModel : BaseViewModel(), ComicInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val repository = Repository()

    private val _comics = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comics: LiveData<UiState<DataResponse<Comic>>>
        get() = _comics

    private val _comicClick = MutableLiveData<EventHandler<Int>>()
    val comicClick: LiveData<EventHandler<Int>>
        get() = _comicClick

    init {
        getAllComics()
    }

    private fun getAllComics() {
        _comics.postValue(UiState.Loading)
        disposeSingle(repository.getAllComics(), ::onGetComicSuccess, ::onError)
    }

    private fun onGetComicSuccess(state: UiState<DataResponse<Comic>>) {
        _comics.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _comics.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        _comicClick.postValue(EventHandler(id))
    }
}