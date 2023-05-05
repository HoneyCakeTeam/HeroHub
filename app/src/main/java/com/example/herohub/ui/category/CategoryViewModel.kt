package com.example.herohub.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.Creator
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.model.Story
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class CategoryViewModel : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    private val repository = Repository()

    private val _characters = MutableLiveData<UiState<DataResponse<Character>>>()
    val characters: LiveData<UiState<DataResponse<Character>>> = _characters

    private val _stories = MutableLiveData<UiState<DataResponse<Story>>>()
    val stories: LiveData<UiState<DataResponse<Story>>> = _stories

    private val _events = MutableLiveData<UiState<DataResponse<Event>>>()
    val events: LiveData<UiState<DataResponse<Event>>> = _events

    private val _comics = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comic: LiveData<UiState<DataResponse<Comic>>> = _comics

    private val _series = MutableLiveData<UiState<DataResponse<Series>>>()
    val series: LiveData<UiState<DataResponse<Series>>> = _series

    private val _creators = MutableLiveData<UiState<DataResponse<Creator>>>()
    val creators: LiveData<UiState<DataResponse<Creator>>> = _creators

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        disposeObservable(repository.getAllCharacters(), ::onGetCharacterSuccess, ::onError)
    }

    private fun getAllComics() {
        disposeObservable(repository.getAllComics(), ::onGetComicsSuccess, ::onError)
    }

    private fun getAllCreators() {
        disposeObservable(repository.getAllCreators(), ::onGetCreatorsSuccess, ::onError)
    }

    private fun getAllEvents() {
        disposeObservable(repository.getAllEvents(), ::onGetEventsSuccess, ::onError)
    }


    private fun getAllSeries() {
        disposeObservable(repository.getAllSeries(), ::onGetSeriesSuccess, ::onError)
    }

    private fun getAllStories() {
        disposeObservable(repository.getAllStories(), ::onGetStoriesSuccess, ::onError)
    }

    private fun onGetCharacterSuccess(state: UiState<DataResponse<Character>>) {
        _characters.postValue(state)
    }

    private fun onGetComicsSuccess(state: UiState<DataResponse<Comic>>) {
        _comics.postValue(state)
    }

    private fun onGetCreatorsSuccess(state: UiState<DataResponse<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetEventsSuccess(state: UiState<DataResponse<Event>>) {
        _events.postValue(state)
    }

    private fun onGetSeriesSuccess(state: UiState<DataResponse<Series>>) {
        _series.postValue(state)
    }

    private fun onGetStoriesSuccess(state: UiState<DataResponse<Story>>) {
        _stories.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        log(throwable.message.toString())
    }
}