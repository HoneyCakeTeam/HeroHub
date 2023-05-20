package com.example.herohub.ui.character

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.local.CharacterEntity
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository
) : BaseViewModel(), CharactersInteractionListener {
    override val TAG: String = this::class.java.simpleName

    private val _characters = MutableLiveData<UiState<List<CharacterEntity>>>()
    val characters: LiveData<UiState<List<CharacterEntity>>>
        get() = _characters

    private val _naserCharacter = MutableLiveData<List<Character>>()
    val naserCharacter: LiveData<List<Character>>
        get() = _naserCharacter

    private val _characterClick = MutableLiveData<EventHandler<Int>>()
    val characterClick: LiveData<EventHandler<Int>>
        get() = _characterClick

    init {
        refreshCharacters()
    }

    private fun refreshCharacters() {
        _characters.postValue(UiState.Loading)
        disposeSingle(marvelRepositoryImp.refreshCharacters(), ::onGetCharacterSuccess, ::onError)
    }



    private fun onGetCharacterSuccess(state: UiState<List<CharacterEntity>>) {
        _characters.value = state
        _naserCharacter.postValue(marvelRepositoryImp.getAllCharacters())
    }

    private fun onError(throwable: Throwable) {
        _characters.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickCharacter(id: Int) {
        _characterClick.postValue(EventHandler(id))
    }
}