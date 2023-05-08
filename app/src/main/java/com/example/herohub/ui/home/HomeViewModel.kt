package com.example.herohub.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.home.adapter.CharactersByAppearanceInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularCharactersInteractionListener
import com.example.herohub.ui.home.adapter.PopularSeriesSliderInteractionListener
import com.example.herohub.ui.home.adapter.SuperHeroesInteractionListener
import com.example.herohub.utills.UiState

class HomeViewModel : BaseViewModel(), MostPopularCharactersInteractionListener,
    CharactersByAppearanceInteractionListener, SuperHeroesInteractionListener,
    PopularSeriesSliderInteractionListener {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }
    private val _homeItems = mutableListOf<HomeItem>()

    private val _characterResponse = MutableLiveData<UiState<DataResponse<Character>>>()
    val characterResponse: LiveData<UiState<DataResponse<Character>>>
        get() = _characterResponse

    private val _seriesResponse = MutableLiveData<UiState<DataResponse<Series>>>()
    val seriesResponse: LiveData<UiState<DataResponse<Series>>>
        get() = _seriesResponse

    private val _eventResponse = MutableLiveData<UiState<DataResponse<Event>>>()
    val eventResponse: LiveData<UiState<DataResponse<Event>>>
        get() = _eventResponse

    private val _comicsResponse = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comicsResponse: LiveData<UiState<DataResponse<Comic>>>
        get() = _comicsResponse

    private val _homeItemsLiveData = MutableLiveData<List<HomeItem>>()
    val homeItemsLiveData: LiveData<List<HomeItem>>
        get() = _homeItemsLiveData

    init {
        getHomeData()
    }

    private fun getHomeData() {
        getAllCharacters()
        getAllSeries()
    }

    private fun getAllCharacters() {
        _characterResponse.postValue(UiState.Loading)
        disposeObservable(
            repository.getAllCharacters(),
            ::onGetCharacterSuccess,
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
        _characterResponse.value = UiState

        val character = _characterResponse.value?.toData()?.results

        val charactersByAppearance = character
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.takeLast(20)

        val superHeroes = character
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.take(15)

        val mostPopularCharacters = character
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.take(20)

        _homeItems.addAll(
            listOf(
                HomeItem.CharactersByAppearance(charactersByAppearance!!),
                HomeItem.SuperHeroes(superHeroes!!),
                HomeItem.MostPopularCharacters(mostPopularCharacters!!)
            )
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onGetSeriesSuccess(UiState: UiState<DataResponse<Series>>) {
        _seriesResponse.value = UiState

        val images = _seriesResponse.value?.toData()?.results
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.shuffled()
            ?.take(10)

        _homeItems.add(
            HomeItem.PopularSeries(images ?: emptyList())
        )

        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onError(throwable: Throwable) {
        _characterResponse.postValue(UiState.Error(throwable.message.toString()))
        _seriesResponse.postValue(UiState.Error(throwable.message.toString()))
        _eventResponse.postValue(UiState.Error(throwable.message.toString()))
        _comicsResponse.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onMostPopularCharactersItemClick(id: Int) {

    }

    override fun onSeriesSliderItemClick(id: Int) {

    }

    override fun onSuperHeroesItemClick(id: Int) {

    }

    override fun onPopularSeriesSliderItemClick(id: Int) {

    }
}