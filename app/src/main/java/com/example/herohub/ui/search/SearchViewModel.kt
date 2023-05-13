package com.example.herohub.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.data.model.Character
import com.example.herohub.data.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()

    val navigateToItem = MutableLiveData<Character>()
    val isNavigated = MutableLiveData<Boolean>()

    val searchQuery = MutableLiveData<String>()

    private val _response = MutableLiveData<UiState<DataResponse<Character>>>()
    val response: LiveData<UiState<DataResponse<Character>>> get() = _response

    val searchResult = MutableLiveData<List<Character>>()

    private val _searchQuery = MediatorLiveData<String>()

    private val searchObservable = Observable.create { emitter ->
        _searchQuery.addSource(
            searchQuery,
            emitter::onNext
        )
    }

    init {
        search()
    }

    @SuppressLint("CheckResult")
    private fun search() {
        searchObservable
            .debounce(
                500,
                TimeUnit.MILLISECONDS
            )
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                log(query)
                performSearch(query)
            }
    }

    private fun performSearch(query: String) {
        if (query.isNotEmpty()) {
            getCharactersByName(query)
        } else {
            searchResult.postValue(emptyList())
        }
    }

    private fun getCharactersByName(name: String) {
        _response.postValue(UiState.Loading)
        disposeSingle(
            repository.getCharactersByName(name),
            ::onGetCharacterSuccess, ::onGetCharacterFailure
        )
    }

    private fun onGetCharacterSuccess(uiState: UiState<DataResponse<Character>>) {
        log(uiState.toData().toString())
        _response.value = uiState
        searchResult.postValue(response.value?.toData()?.results ?: emptyList())
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _response.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun <T> onClickItem(item: T) {
        isNavigated.value = true
        navigateToItem.value = item as Character
    }

    fun onCompleteNavigation() {
        isNavigated.value = false
    }
}