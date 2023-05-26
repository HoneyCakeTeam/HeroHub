package com.example.herohub.ui.characterdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
    state: SavedStateHandle,
) : BaseViewModel(),
    ComicsInteractionListener, SeriesInteractionListener, EventsInteractionListener {

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
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(TAG, "getComicsOfCharacter: ${Thread.currentThread().name}")
            marvelRepositoryImp.getCharacterComics(characterArgs.characterId).collect {
                withContext(Dispatchers.Main) {
                    onGetCharacterComics(it)
                }
            }
        }
    }

    private fun onGetCharacterComics(state: UiState<List<Comic>>) {
        _characterComics.value = state
        _characterComics.value?.toData()?.let {
            CharacterDetailsItem.CharacterComics(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }


    private fun getEventOfCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(TAG, "getEventOfCharacter: ${Thread.currentThread().name}")
            marvelRepositoryImp.getCharacterEvents(characterArgs.characterId).collect {
                withContext(Dispatchers.Main) {
                    onGetCharacterEvents(it)
                }
            }
        }
    }

    private fun onGetCharacterEvents(uiState: UiState<List<Event>>) {
        _characterEvent.value = uiState
        _characterEvent.value?.toData()?.let {
            CharacterDetailsItem.CharacterEvents(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }


    private fun getSeriesOfCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(TAG, "getSeriesOfCharacter: ${Thread.currentThread().name}")
            marvelRepositoryImp.getCharacterSeries(characterArgs.characterId).collect {
                withContext(Dispatchers.Main) {
                    onGetCharacterSeries(it)
                }
            }
        }
    }

    private fun onGetCharacterSeries(uiState: UiState<List<Series>>) {
        _characterSeries.value = uiState
        _characterSeries.value?.toData()?.let {
            CharacterDetailsItem.CharacterSeries(it)
        }?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
    }


    private fun getDetailsOfCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(TAG, "getDetailsOfCharacter: ${Thread.currentThread().name}")
            marvelRepositoryImp.getCharacterDetails(characterArgs.characterId).collect {
                withContext(Dispatchers.Main) {
                    onGetCharacterDetails(it)
                }
            }
        }
    }

    private fun onGetCharacterDetails(state: UiState<List<Character>>) {
        _characterDetails.value = state
        characterItem.value = characterDetails.value?.toData()?.firstOrNull()
        _isFavorite.value = marvelRepositoryImp.isFavorite(characterItem.value?.id.toString())
        characterItem.value?.let { CharacterDetailsItem.CharacterInfo(it) }
            ?.let { characterDetailsItem.add(it) }
        _characterItemsLiveData.postValue(characterDetailsItem)
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
                CharacterDetailsUiEvent.ClickCharacterEvents(id)
            )
        )
    }

    override fun onClickSeries(id: Int) {
        _characterDetailsUiEvent.postValue(
            EventHandler(CharacterDetailsUiEvent.ClickCharacterSeries(id))
        )
    }

}