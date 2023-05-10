package com.example.herohub.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
class CharacterDetailsViewModel : BaseViewModel(), ComicsInteractionListener {
    private val repository = Repository()

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


    override val TAG: String
        get() = this::class.simpleName.toString()


    fun getComicsOfCharacter(characterId: Int) {
        disposeObservable(
            repository.getCharacterComics(characterId),
            ::onGetCharacterComicsSuccess,
            ::onError
        )
    }

    fun getSeriesOfCharacter(characterId: Int) {
        disposeObservable(
            repository.getCharacterSeries(characterId),
            ::onGetCharacterSeriesSuccess,
            ::onError
        )
    }

    private fun onGetCharacterSeriesSuccess(uiState: UiState<DataResponse<Series>>) {
        _characterSeries.postValue(uiState)
    }

    fun getDetailsOfCharacter(characterId: Int) {
        disposeObservable(
            repository.getCharacterDetails(characterId),
            ::onGetCharacterDetailsSuccess,
            ::onError
        )
    }


    private fun onGetCharacterDetailsSuccess(state: UiState<DataResponse<Character>>) {
        _characterDetails.postValue(state)

    }

    private fun onGetCharacterComicsSuccess(state: UiState<DataResponse<Comic>>) {
        _characterComics.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _characterComics.postValue(UiState.Error(throwable.message.toString()))
        _characterDetails.postValue(UiState.Error(throwable.message.toString()))
        _characterSeries.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        _navigateToComicDetails.postValue(id)
    }
}