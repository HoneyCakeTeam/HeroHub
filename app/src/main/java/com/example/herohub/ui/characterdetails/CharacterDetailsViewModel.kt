package com.example.herohub.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.Repository
import com.example.herohub.data.model.Character
import com.example.herohub.data.model.Comic
import com.example.herohub.data.model.DataResponse
import com.example.herohub.data.model.Event
import com.example.herohub.data.model.FavoriteItem
import com.example.herohub.data.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.characterdetails.adapter.ComicsInteractionListener
import com.example.herohub.ui.characterdetails.adapter.EventsInteractionListener
import com.example.herohub.ui.characterdetails.adapter.SeriesInteractionListener
import com.example.herohub.utills.EventHandler
import com.example.herohub.utills.UiState


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
class CharacterDetailsViewModel(state: SavedStateHandle) : BaseViewModel(),
    ComicsInteractionListener, SeriesInteractionListener, EventsInteractionListener {
    private val repository = Repository()
    private val characterArgs = CharacterDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _characterEvent = MutableLiveData<UiState<DataResponse<Event>>>()

    private val _characterComics = MutableLiveData<UiState<DataResponse<Comic>>>()

    private val _characterSeries = MutableLiveData<UiState<DataResponse<Series>>>()

    private val _characterDetails = MutableLiveData<UiState<DataResponse<Character>>>()
    private val characterDetails: LiveData<UiState<DataResponse<Character>>>
        get() = _characterDetails


    private val characterItem = MutableLiveData<Character>()

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite


    private val _characterItemsLiveData = MutableLiveData<List<CharacterDetailsItem>>()
    val characterItemsLiveData: LiveData<List<CharacterDetailsItem>> = _characterItemsLiveData

    var characterDetailsItem = mutableListOf<CharacterDetailsItem>()

    private val _characterDetailsUiEvent =
        MutableLiveData<EventHandler<CharacterDetailsUiEvent?>>(EventHandler(null))
    val characterDetailsUiEvent: LiveData<EventHandler<CharacterDetailsUiEvent?>>
        get() = _characterDetailsUiEvent


    init {
        getDetailsOfCharacter()
        getComicsOfCharacter()
        getEventOfCharacter()
        getSeriesOfCharacter()
    }

    override val TAG: String
        get() = this::class.simpleName.toString()

    private fun getComicsOfCharacter() {
        disposeSingle(
            repository.getCharacterComics(characterArgs.characterId),
            ::onGetCharacterComicsSuccess,
            ::onError
        )
    }

    private fun getEventOfCharacter() {
        disposeSingle(
            repository.getCharacterEvents(characterArgs.characterId),
            ::onGetCharacterEventsSuccess,
            ::onError
        )
    }

    private fun getSeriesOfCharacter() {
        disposeSingle(
            repository.getCharacterSeries(characterArgs.characterId),
            ::onGetCharacterSeriesSuccess,
            ::onError
        )
    }

    private fun getDetailsOfCharacter() {
        disposeSingle(
            repository.getCharacterDetails(characterArgs.characterId),
            ::onGetCharacterDetailsSuccess,
            ::onError
        )
    }

    private fun onGetCharacterEventsSuccess(uiState: UiState<DataResponse<Event>>) {
        _characterEvent.value = uiState
        _characterEvent.value?.toData()?.results?.let {
            CharacterDetailsItem.CharacterEvents(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }

    private fun onGetCharacterSeriesSuccess(uiState: UiState<DataResponse<Series>>) {
        _characterSeries.value = uiState
        _characterSeries.value?.toData()?.results?.let {
            CharacterDetailsItem.CharacterSeries(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }

    private fun onGetCharacterComicsSuccess(state: UiState<DataResponse<Comic>>) {
        _characterComics.value = state
        _characterComics.value?.toData()?.results?.let {
            CharacterDetailsItem.CharacterComics(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }

    private fun onGetCharacterDetailsSuccess(state: UiState<DataResponse<Character>>) {
        _characterDetails.value = state
        characterItem.value = characterDetails.value?.toData()?.results?.firstOrNull()
        _isFavorite.value = repository.isFavorite(characterItem.value?.id.toString())
        characterItem.value?.let { CharacterDetailsItem.CharacterInfo(it) }
            ?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }


    private fun onError(throwable: Throwable) {
        _characterComics.postValue(UiState.Error(throwable.message.toString()))
        _characterDetails.postValue(UiState.Error(throwable.message.toString()))
        _characterSeries.postValue(UiState.Error(throwable.message.toString()))
        _characterEvent.postValue(UiState.Error(throwable.message.toString()))
    }


    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            characterItem.value?.id.toString(),
            characterItem.value?.name.toString(),
            characterItem.value?.thumbnail?.path.toString(),
            FavoriteItem.Type.CHARACTER
        )

        if (isFavorite.value == true) {
            repository.removeFavorite(favoriteItem)
            _isFavorite.value = repository.isFavorite(characterItem.value?.id.toString())
        } else {
            repository.addToFavorite(favoriteItem)
            _isFavorite.value = repository.isFavorite(characterItem.value?.id.toString())
        }
    }

    override fun onClickComic(id: Int) {
        _characterDetailsUiEvent.postValue(
            EventHandler(
                CharacterDetailsUiEvent.ClickCharacterComic(
                    id
                )
            )
        )
    }

    override fun onClickEvent(id: Int) {
        _characterDetailsUiEvent.postValue(
            EventHandler(
                CharacterDetailsUiEvent.ClickCharacterEvents(
                    id
                )
            )
        )
    }

    override fun onClickSeries(id: Int) {
        _characterDetailsUiEvent.postValue(
            EventHandler(
                CharacterDetailsUiEvent.ClickCharacterSeries(
                    id
                )
            )
        )
    }

}