package com.example.herohub.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Comic
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllComics().collect {
                onGetComic(it)
            }
        }
    }

    private fun onGetComic(state: UiState<List<Comic>>) {
        _comics.postValue(state)
    }

    override fun onClickComic(id: Int) {
        _comicClick.postValue(EventHandler(id))
    }
}