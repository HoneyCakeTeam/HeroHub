package com.example.herohub.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import com.example.herohub.utills.handleThreadsAndSubscribe


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
class CharacterDetailsViewModel : BaseViewModel(), ComicsInteractionListener {
    private val repository = Repository()

    private val _characterComics = MutableLiveData<UiState<BaseResponse<Comic>?>>(UiState.Loading)
    val characterComics: LiveData<UiState<BaseResponse<Comic>?>>
        get() = _characterComics

    private val _characterDetails =
        MutableLiveData<UiState<BaseResponse<Character>?>>(UiState.Loading)
    val characterDetails: LiveData<UiState<BaseResponse<Character>?>> = _characterDetails

    private val _navigateToComicDetails = MutableLiveData<Int>(0)
    val navigateToComicDetails: LiveData<Int> = _navigateToComicDetails


    override val TAG: String
        get() = this::class.simpleName.toString()


    fun getComicsOfCharacter(characterId: Int) {
        repository.getCharacterComics(characterId)
            .handleThreadsAndSubscribe(::onGetCharacterComicsSuccess, ::onGetCharacterComicsFailed)
            .addToCompositeDisposable()
    }

    fun getDetailsOfCharacter(characterId: Int) {
        repository.getCharacterDetails(characterId)
            .handleThreadsAndSubscribe(
                ::onGetCharacterDetailsSuccess,
                ::onGetCharacterDetailsFailed
            )
    }

    private fun onGetCharacterDetailsSuccess(state: UiState<BaseResponse<Character>?>) {
        _characterDetails.postValue(state)
    }

    private fun onGetCharacterDetailsFailed(throwable: Throwable) {
        _characterDetails.postValue(UiState.Error(throwable.message.toString()))
    }

    private fun onGetCharacterComicsSuccess(state: UiState<BaseResponse<Comic>?>) {
        _characterComics.postValue(state)
    }

    private fun onGetCharacterComicsFailed(throwable: Throwable) {
        _characterComics.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        _navigateToComicDetails.postValue(id)
    }
}