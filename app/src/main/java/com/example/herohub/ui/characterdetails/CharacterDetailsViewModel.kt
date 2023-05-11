package com.example.herohub.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Event
import com.example.herohub.model.FavoriteItem
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.characterdetails.adapter.ComicsInteractionListener
import com.example.herohub.utills.UiState


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
class CharacterDetailsViewModel : BaseViewModel(), ComicsInteractionListener {
    private val repository = Repository()

    private val _characterEvent = MutableLiveData<UiState<DataResponse<Event>>>()
    val characterEvent: LiveData<UiState<DataResponse<Event>>>
        get() = _characterEvent

    private val _characterComics = MutableLiveData<UiState<DataResponse<Comic>>>()
    val characterComics: LiveData<UiState<DataResponse<Comic>>>
        get() = _characterComics

    private val _characterSeries = MutableLiveData<UiState<DataResponse<Series>>>()
    val characterSeries: LiveData<UiState<DataResponse<Series>>>
        get() = _characterSeries

    private val _characterDetails = MutableLiveData<UiState<DataResponse<Character>>>()
    val characterDetails: LiveData<UiState<DataResponse<Character>>>
        get() = _characterDetails


    private val _navigateToComicDetails = MutableLiveData(0)
    val navigateToComicDetails: LiveData<Int> = _navigateToComicDetails

    private val characterItem = MutableLiveData<Character>()
    val isFavorite = MutableLiveData<Boolean>()

    override val TAG: String
        get() = this::class.simpleName.toString()

    fun getEventsOfCharacter(characterId: Int) {
        disposeSingle(
            repository.getCharacterEvents(characterId),
            ::onGetCharacterEventsSuccess,
            ::onError
        )
    }

    fun getComicsOfCharacter(characterId: Int) {
        disposeSingle(
            repository.getCharacterComics(characterId),
            ::onGetCharacterComicsSuccess,
            ::onError
        )
    }

    fun getSeriesOfCharacter(characterId: Int) {
        disposeSingle(
            repository.getCharacterSeries(characterId),
            ::onGetCharacterSeriesSuccess,
            ::onError
        )
    }

    fun getDetailsOfCharacter(characterId: Int) {
        disposeSingle(
            repository.getCharacterDetails(characterId),
            ::onGetCharacterDetailsSuccess,
            ::onError
        )
    }

    private fun onGetCharacterEventsSuccess(uiState: UiState<DataResponse<Event>>) {
        _characterEvent.postValue(uiState)
    }

    private fun onGetCharacterSeriesSuccess(uiState: UiState<DataResponse<Series>>) {
        _characterSeries.postValue(uiState)
    }

    private fun onGetCharacterComicsSuccess(state: UiState<DataResponse<Comic>>) {
        _characterComics.postValue(state)
    }

    private fun onGetCharacterDetailsSuccess(state: UiState<DataResponse<Character>>) {
        _characterDetails.value = state
        characterItem.value = characterDetails.value?.toData()?.results?.firstOrNull()
        isFavorite.value = repository.isFavorite(characterItem.value?.id.toString())
    }


    private fun onError(throwable: Throwable) {
        _characterComics.postValue(UiState.Error(throwable.message.toString()))
        _characterDetails.postValue(UiState.Error(throwable.message.toString()))
        _characterSeries.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        _navigateToComicDetails.postValue(id)
    }


    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            characterItem.value?.id.toString(),
            characterItem.value?.name.toString(),
            characterItem.value?.thumbnail?.path.toString()
        )

        if (isFavorite.value == true) {
            repository.removeFavorite(favoriteItem)
            isFavorite.postValue(false)
        } else {
            repository.addToFavorite(favoriteItem)
            isFavorite.postValue(true)
        }
    }

}