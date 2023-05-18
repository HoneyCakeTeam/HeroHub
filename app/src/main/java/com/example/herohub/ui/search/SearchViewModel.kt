package com.example.herohub.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val marvelRepositoryImp = MarvelRepositoryImp()

    private val _eventClick = MutableLiveData<EventHandler<Character>>()
    val eventClick: LiveData<EventHandler<Character>>
        get() = _eventClick

    val searchQuery = MutableLiveData<String>()

    private val _response = MutableLiveData<UiState<List<Character>>>()
    val response: LiveData<UiState<List<Character>>> get() = _response

    val searchResult = MediatorLiveData<List<Character>>()

    private val searchQuerySubject = PublishSubject.create<String>()

    init {
        searchByMediatorLiveData()
    }

    @SuppressLint("CheckResult")
    private fun searchByMediatorLiveData() {
        _response.postValue(UiState.Loading)
        searchQuerySubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe { name ->
                findCharacters(name)
            }
        searchResult.addSource(searchQuery) { query ->
            searchQuerySubject.onNext(query)
        }
    }

    private fun findCharacters(name: String) {
        disposeSingle(
            marvelRepositoryImp.getCharactersByName(name),
            ::onGetCharacterSuccess, ::onGetCharacterFailure
        )
    }

    private fun onGetCharacterSuccess(uiState: UiState<List<Character>>) {
        log(uiState.toData().toString())
        _response.value = uiState
        searchResult.postValue(response.value?.toData() ?: emptyList())
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _response.postValue(UiState.Error(throwable.message.toString()))
        searchResult.postValue(emptyList())
    }

    override fun <T> onClickItem(item: T) {
        _eventClick.postValue(EventHandler(item as Character))
    }
}