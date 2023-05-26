package com.example.herohub.ui.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
) : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()

    private val _eventClick = MutableLiveData<EventHandler<Character>>()
    val eventClick: LiveData<EventHandler<Character>>
        get() = _eventClick

    val searchQuery = MutableLiveData<String>()

    private val _response = MutableLiveData<UiState<List<Character>>>()
    val response: LiveData<UiState<List<Character>>> get() = _response

    val searchResult = MediatorLiveData<List<Character>>()

    private val searchQueryChannel = Channel<String>()

    init {
        searchByMediatorLiveData()
    }

    @SuppressLint("CheckResult")
    private fun searchByMediatorLiveData() {
        /* _response.postValue(UiState.Loading)
         searchQuerySubject
             .debounce(500, TimeUnit.MILLISECONDS)
             .subscribe { name ->
                 findCharacters(name)
             }
         searchResult.addSource(searchQuery) { query ->
             searchQuerySubject.onNext(query)
         }*/
        viewModelScope.launch {
            searchQueryChannel.consumeAsFlow()
                .debounce(500)
                .collect { name ->
                    findCharacters(name)
                }
        }

        searchResult.addSource(searchQuery) { query ->
            viewModelScope.launch {
                searchQueryChannel.send(query)
            }
        }
    }

    private fun findCharacters(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getCharactersByName(name).collect {
                withContext(Dispatchers.Main) {
                    onGetCharacter(it)
                }
            }
        }
    }

    fun saveSearchHistory(keyword: String) {
        viewModelScope.launch {
            marvelRepositoryImp.saveSearchKeyword(keyword)
        }
        Log.e("TAG", "saveSearchHistory: $keyword")
    }

    private fun onGetCharacter(uiState: UiState<List<Character>>) {
        log(uiState.toData().toString())
        _response.value = uiState
        searchResult.postValue(response.value?.toData() ?: emptyList())
    }

    override fun <T> onClickItem(item: T) {
        _eventClick.postValue(EventHandler(item as Character))
    }
}