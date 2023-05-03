package com.example.herohub.ui.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import com.example.herohub.utills.handleThreadsAndSubscribe


/**
 * Created by Aziza Helmy on 5/3/2023.
 */
class CharacterDetailsViewModel : BaseViewModel() {
    private val repository = Repository()

    private val _characterComics = MutableLiveData<UiState<BaseResponse<Comic>?>>(UiState.Loading)
    val characterComics: LiveData<UiState<BaseResponse<Comic>?>>
        get() = _characterComics

    init {
        getComicsOfCharacter(1011334)
    }

    override val TAG: String
        get() = this::class.simpleName.toString()


    private fun getComicsOfCharacter(characterId: Int) {
        repository.getCharacterComics(characterId)
            .handleThreadsAndSubscribe(::onGetCharacterComicsSuccess, ::onGetCharacterComicsFailed)
            .addToCompositeDisposable()
    }

    private fun onGetCharacterComicsSuccess(state: UiState<BaseResponse<Comic>?>) {
        _characterComics.postValue(state)
    }

    private fun onGetCharacterComicsFailed(throwable: Throwable) {
        _characterComics.postValue(UiState.Error(throwable.message.toString()))
    }
}