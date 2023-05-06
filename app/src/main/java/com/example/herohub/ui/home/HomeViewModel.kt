package com.example.herohub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.home.adapter.MostPopularCharactersInteractionListener
import com.example.herohub.ui.home.adapter.SliderInteractionListener
import com.example.herohub.ui.home.adapter.SuperHeroesInteractionListener
import com.example.herohub.utills.UiState

class HomeViewModel : BaseViewModel(), MostPopularCharactersInteractionListener,
    SliderInteractionListener, SuperHeroesInteractionListener {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }

    private val _characterResponse = MutableLiveData<UiState<DataResponse<Character>>>()
    val characterResponse: LiveData<UiState<DataResponse<Character>>>
        get() = _characterResponse

    private val _seriesResponse = MutableLiveData<UiState<DataResponse<Series>>>()
    val seriesResponse: LiveData<UiState<DataResponse<Series>>>
        get() = _seriesResponse

    private val _mostPopularCharactersResponse = MutableLiveData<UiState<DataResponse<Comic>>>()
    val mostPopularCharactersResponse: LiveData<UiState<DataResponse<Comic>>>
        get() = _mostPopularCharactersResponse

    init {
        getHomeData()
    }

    private fun getHomeData() {
        getAllCharacters()
        getMostPopularCharacters()
        getAllSeries()
    }

    private fun getAllCharacters() {
        disposeObservable(
            repository.getAllCharacters(),
            ::onGetCharacterSuccess,
            ::onError
        )
    }

    private fun getMostPopularCharacters() {
        disposeObservable(
            repository.getAllComics(),
            ::onGetMostPopularCharactersSuccess,
            ::onError
        )
    }

    private fun getAllSeries() {
        disposeObservable(
            repository.getAllSeries(),
            ::onGetSeriesSuccess,
            ::onError
        )
    }

    private fun onGetCharacterSuccess(UiState: UiState<DataResponse<Character>>) {
        _characterResponse.postValue(UiState)
    }

    private fun onGetMostPopularCharactersSuccess(UiState: UiState<DataResponse<Comic>>) {
        _mostPopularCharactersResponse.postValue(UiState)
    }

    private fun onGetSeriesSuccess(UiState: UiState<DataResponse<Series>>) {
        _seriesResponse.postValue(UiState)
    }

    private fun onError(throwable: Throwable) {
        _characterResponse.postValue(UiState.Error(throwable.message.toString()))
        _mostPopularCharactersResponse.postValue(UiState.Error(throwable.message.toString()))
        _seriesResponse.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onMostPopularCharactersItemClick(id: Int) {

    }

    override fun onSeriesSliderItemClick(id: Int) {

    }

    override fun onSuperHeroesItemClick(id: Int) {

    }
}