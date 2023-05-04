package com.example.herohub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class HomeViewModel : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }

    private val _characterResponse = MutableLiveData<UiState<DataResponse<Character>?>>()
    val characterResponse: LiveData<UiState<DataResponse<Character>?>>
        get() = _characterResponse

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _characterResponse.postValue(UiState.Loading)
        disposeObservable(
            repository.getAllCharacters(),
            ::onGetCharacterSuccess,
            ::onGetCharacterError
        )
    }

    private fun onGetCharacterSuccess(UiState: UiState<DataResponse<Character>?>) {
        _characterResponse.postValue(UiState)
        log(UiState.toData().toString())
    }

    private fun onGetCharacterError(errorMessage: Throwable) =
        log(errorMessage.message.toString())
}