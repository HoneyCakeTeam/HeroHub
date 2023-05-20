package com.example.herohub.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Comic
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val marvelRepositoryImp: MarvelRepository) :
    BaseViewModel(), ComicInteractionListener {
    override val TAG: String = this::class.java.simpleName

    private val _comics = MutableLiveData<UiState<List<Comic>>>()
    val comics: LiveData<UiState<List<Comic>>>
        get() = _comics

    private val _comicClick = MutableLiveData<EventHandler<Int>>()
    val comicClick: LiveData<EventHandler<Int>>
        get() = _comicClick

    init {
        getAllComics()
    }

    private fun getAllComics() {
        _comics.postValue(UiState.Loading)
       disposeSingle(marvelRepositoryImp.getAllComics(), ::onGetComicSuccess, ::onError)
    }

    private fun onGetComicSuccess(state: UiState<List<Comic>>) {
        _comics.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _comics.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        _comicClick.postValue(EventHandler(id))
    }
}