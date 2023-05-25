package com.example.herohub.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
) : BaseViewModel(), CharactersInteractionListener {
    override val TAG: String = this::class.java.simpleName

    private val _characters = MutableLiveData<UiState<List<Character>>>()
    val characters: LiveData<UiState<List<Character>>>
        get() = _characters

    private val _characterClick = MutableLiveData<EventHandler<Int>>()
    val characterClick: LiveData<EventHandler<Int>>
        get() = _characterClick

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllCharacters().collect {
                onGetCharacter(it)
            }
        }
    }

    private fun onGetCharacter(state: UiState<List<Character>>) {
        _characters.postValue(state)
    }

    override fun onClickCharacter(id: Int) {
        _characterClick.postValue(EventHandler(id))
    }
}