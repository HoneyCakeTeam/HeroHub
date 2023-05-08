package com.example.herohub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState


class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()

    val navigateToItem = MutableLiveData<Character>()
    val isNavigated = MutableLiveData<Boolean>()

    val searchQuery = MutableLiveData<String>()

    private val _response = MutableLiveData<UiState<DataResponse<Character>>>()
    val response: LiveData<UiState<DataResponse<Character>>> get() = _response

    val searchResult = MediatorLiveData<List<Character>>().apply {
        addSource(searchQuery, this@SearchViewModel::search)
    }


    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _response.postValue(UiState.Loading)
        disposeObservable(
            repository.getAllCharacters(),
            ::onGetCharacterSuccess, ::onGetCharacterFailure
        )
    }

    fun search(query: String) {
        log(query)

        if (query.isNotEmpty()) {
            searchResult.postValue(response.value?.toData()?.results!!.filter {
                it.name!!.contains(query, ignoreCase = true)
            })
        } else {
            searchResult.postValue(emptyList())
        }
    }

    private fun onGetCharacterSuccess(uiState: UiState<DataResponse<Character>>) {
        log(uiState.toData().toString())
        _response.postValue(uiState)

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