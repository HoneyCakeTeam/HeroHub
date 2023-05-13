package com.example.herohub.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.data.model.Character
import com.example.herohub.data.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState

class CharactersViewModel : BaseViewModel(), CharactersInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val repository = Repository()

    private val _characters = MutableLiveData<UiState<DataResponse<Character>>>()
    val characters: LiveData<UiState<DataResponse<Character>>>
        get() = _characters

    private val _characterClick = MutableLiveData<EventHandler<Int>>()
    val characterClick: LiveData<EventHandler<Int>>
        get() = _characterClick

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _characters.postValue(UiState.Loading)
        disposeSingle(repository.getAllCharacters(), ::onGetCharacterSuccess, ::onError)
    }

    private fun onGetCharacterSuccess(state: UiState<DataResponse<Character>>) {
        _characters.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _characters.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickCharacter(id: Int) {
        _characterClick.postValue(EventHandler(id))
    }
}