package com.example.herohub.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.characterdetails.adapter.ComicsInteractionListener
import com.example.herohub.ui.characterdetails.adapter.EventsInteractionListener
import com.example.herohub.ui.characterdetails.adapter.SeriesInteractionListener
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
class CharacterDetailsViewModel(state: SavedStateHandle) : BaseViewModel(),
    ComicsInteractionListener, SeriesInteractionListener, EventsInteractionListener {
    private val marvelRepositoryImp = MarvelRepositoryImp()
    private val characterArgs = CharacterDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _characterEvent = MutableLiveData<UiState<List<Event>>>()

    private val _characterComics = MutableLiveData<UiState<List<Comic>>>()

    private val _characterSeries = MutableLiveData<UiState<List<Series>>>()

    private val _characterDetails = MutableLiveData<UiState<List<Character>>>()
    private val characterDetails: LiveData<UiState<List<Character>>>
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
            marvelRepositoryImp.getCharacterComics(characterArgs.characterId),
            ::onGetCharacterComicsSuccess,
            ::onError
        )
    }

    private fun getEventOfCharacter() {
        disposeSingle(
            marvelRepositoryImp.getCharacterEvents(characterArgs.characterId),
            ::onGetCharacterEventsSuccess,
            ::onError
        )
    }

    private fun getSeriesOfCharacter() {
        disposeSingle(
            marvelRepositoryImp.getCharacterSeries(characterArgs.characterId),
            ::onGetCharacterSeriesSuccess,
            ::onError
        )
    }

    private fun getDetailsOfCharacter() {
        disposeSingle(
            marvelRepositoryImp.getCharacterDetails(characterArgs.characterId),
            ::onGetCharacterDetailsSuccess,
            ::onError
        )
    }

    private fun onGetCharacterEventsSuccess(uiState: UiState<List<Event>>) {
        _characterEvent.value = uiState
        _characterEvent.value?.toData()?.let {
            CharacterDetailsItem.CharacterEvents(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }

    private fun onGetCharacterSeriesSuccess(uiState: UiState<List<Series>>) {
        _characterSeries.value = uiState
        _characterSeries.value?.toData()?.let {
            CharacterDetailsItem.CharacterSeries(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }

    private fun onGetCharacterComicsSuccess(state: UiState<List<Comic>>) {
        _characterComics.value = state
        _characterComics.value?.toData()?.let {
            CharacterDetailsItem.CharacterComics(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }

    private fun onGetCharacterDetailsSuccess(state: UiState<List<Character>>) {
        _characterDetails.value = state
        characterItem.value = characterDetails.value?.toData()?.firstOrNull()
        _isFavorite.value = marvelRepositoryImp.isFavorite(characterItem.value?.id.toString())
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
            characterItem.value?.imageUrl.toString(),
            FavoriteItem.Type.CHARACTER
        )

        if (isFavorite.value == true) {
            marvelRepositoryImp.removeFavorite(favoriteItem)
            _isFavorite.value = marvelRepositoryImp.isFavorite(characterItem.value?.id.toString())
        } else {
            marvelRepositoryImp.addToFavorite(favoriteItem)
            _isFavorite.value = marvelRepositoryImp.isFavorite(characterItem.value?.id.toString())
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