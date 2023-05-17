package com.example.herohub.ui.home

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.remote.model.Character
import com.example.herohub.data.remote.model.Comic
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.data.remote.model.Event
import com.example.herohub.data.remote.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.home.adapter.MostPopularComicsInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularEventsInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularSeriesInteractionListener
import com.example.herohub.ui.home.adapter.SliderInteractionListener
import com.example.herohub.ui.home.adapter.SuperHeroesInteractionListener
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState

class HomeViewModel : BaseViewModel(), MostPopularSeriesInteractionListener,
    SuperHeroesInteractionListener,
    SliderInteractionListener, MostPopularEventsInteractionListener,
    MostPopularComicsInteractionListener {

    private lateinit var state: Parcelable
    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val marvelRepository: MarvelRepository by lazy { MarvelRepository() }
    private val _homeItems = mutableListOf<HomeItem>()

    private val _characterResponse =
        MutableLiveData<UiState<DataResponse<Character>>>()
    val characterResponse: LiveData<UiState<DataResponse<Character>>>
        get() = _characterResponse

    private val _homeUiEvent = MutableLiveData<EventHandler<HomeUiEvent?>>(EventHandler(null))
    val homeUIEvent: LiveData<EventHandler<HomeUiEvent?>>
        get() = _homeUiEvent

    private val _seriesResponse = MutableLiveData<UiState<DataResponse<Series>>>()

    private val _eventResponse = MutableLiveData<UiState<DataResponse<Event>>>()


    private val _comicsResponse = MutableLiveData<UiState<DataResponse<Comic>>>()

    private val _homeItemsLiveData = MutableLiveData<List<HomeItem>>()
    val homeItemsLiveData: LiveData<List<HomeItem>>
        get() = _homeItemsLiveData

    init {
        getHomeData()
    }

    private fun getHomeData() {
        getSliderItems()
        getAllCharacters()
        getMostPopularSeries()
        getAllEvents()
        getAllComics()
    }

    private fun getAllCharacters() {
        _characterResponse.postValue(UiState.Loading)
        disposeSingle(
            marvelRepository.getAllCharacters(),
            ::onGetCharacterSuccess,
            ::onError
        )
    }

    private fun getSliderItems() {
        _characterResponse.postValue(UiState.Loading)
        disposeSingle(
            marvelRepository.getAllEvents(),
            ::onGetSliderItemsSuccess,
            ::onError
        )
    }

    private fun getMostPopularSeries() {
        _characterResponse.postValue(UiState.Loading)
        disposeSingle(
            marvelRepository.getAllSeries(),
            ::onGetMostPopularSeriesSuccess,
            ::onError
        )
    }

    private fun getAllEvents() {
        _characterResponse.postValue(UiState.Loading)
        disposeSingle(
            marvelRepository.getAllEvents(),
            ::onGetEventSuccess,
            ::onError
        )
    }

    private fun getAllComics() {
        _characterResponse.postValue(UiState.Loading)
        disposeSingle(
            marvelRepository.getAllComics(),
            ::onGetComicsSuccess,
            ::onError
        )
    }

    private fun onGetComicsSuccess(UiState: UiState<DataResponse<Comic>>) {
        _comicsResponse.value = UiState
        val comic = _comicsResponse.value?.toData()?.results
        val mostPopularComics = comic
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.take(20)
        _homeItems.add(
            (HomeItem.MostPopularComics(mostPopularComics!!))
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onGetCharacterSuccess(UiState: UiState<DataResponse<Character>>) {
        _characterResponse.value = UiState

        val character = _characterResponse.value?.toData()?.results

        val superHeroes = character
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.take(15)

        _homeItems.addAll(
            listOf(
                HomeItem.SuperHeroes(superHeroes!!)
            )
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onGetSliderItemsSuccess(UiState: UiState<DataResponse<Event>>) {
        _eventResponse.value = UiState
        val images = _eventResponse.value?.toData()?.results
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.shuffled()
            ?.take(10)
        _homeItems.add(
            HomeItem.Slider(images ?: emptyList())
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onGetMostPopularSeriesSuccess(UiState: UiState<DataResponse<Series>>) {
        _seriesResponse.value = UiState
        val series = _seriesResponse.value?.toData()?.results
            ?.filterNot { it.thumbnail?.path?.contains("image_not_available") ?: false }
            ?.take(10)
        _homeItems.add(
            HomeItem.MostPopularSeries(series!!)
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onGetEventSuccess(uiState: UiState<DataResponse<Event>>) {
        _eventResponse.value = uiState
        val events = _eventResponse.value?.toData()?.results
            ?.filterNot { it.thumbnail?.path?.contains("event_not_found") ?: false }
            ?.shuffled()
            ?.take(10)

        _homeItems.add(
            HomeItem.MostPopularEvents(events ?: emptyList())
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun onError(throwable: Throwable) {
        _characterResponse.postValue(UiState.Error(throwable.message.toString()))
        _seriesResponse.postValue(UiState.Error(throwable.message.toString()))
        _eventResponse.postValue(UiState.Error(throwable.message.toString()))
        _comicsResponse.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onMostPopularSeriesItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickMostPopularSeriesItem(id)))
    }

    override fun onSeeAllSeriesClick() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllSeriesEvent))
    }

    override fun onSuperHeroesItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickCharacterEvent(id)))
    }

    override fun onSeeAllCharactersClicked() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllCharacterEvent))
    }

    override fun onSliderItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSliderItemEvent(id)))
    }

    override fun onMostPopularEventClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickEventEvent(id)))
    }

    override fun onClickSeeAllEvents() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllEventsEvent))
    }

    override fun onMostPopularComicsItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickComicEvent(id)))

    }

    override fun onCLickSeeAllComics() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllComicsEvent))
    }

}