package com.example.herohub.ui.category.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class CharactersViewModel : BaseViewModel() , CharactersInteractionListener {
    override val TAG: String = this::class.java.simpleName

    private val repository = Repository()

    private val _characters = MutableLiveData<UiState<DataResponse<Character>>>()
    val characters: LiveData<UiState<DataResponse<Character>>>
        get() = _characters

    init {
        log("First time")
        getAllCharacters()
    }

    private fun getAllCharacters() {
        disposeObservable(repository.getAllCharacters(), ::onGetCharacterSuccess, ::onError)
    }

    private fun onGetCharacterSuccess(state: UiState<DataResponse<Character>>) {
        _characters.postValue(state)
        log("Success : ${state.toData()?.results?.size}")
    }

    private fun onError(throwable: Throwable) {
        log("Error : ${throwable.message.toString()}")
    }

    override fun onClickItem(id: Int) {
        log("Character $id")
    }
}