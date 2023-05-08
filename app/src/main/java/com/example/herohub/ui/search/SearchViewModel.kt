package com.example.herohub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState


class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()

    private val _response = MutableLiveData<UiState<DataResponse<Character>>>()
    val response: LiveData<UiState<DataResponse<Character>>> get() = _response

    private val _searchResult = MutableLiveData<List<Character>>()
    val searchResult: LiveData<List<Character>>
        get() = _searchResult

    init {
        _searchResult.value = mutableListOf()
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
            _searchResult.value = response.value?.toData()?.results!!.filter {
                it.name!!.contains(query, ignoreCase = true)
            }
        } else {
            _searchResult.postValue(emptyList())
        }
        log(_searchResult.value!!.size.toString())
    }

    private fun onGetCharacterSuccess(uiState: UiState<DataResponse<Character>>) {
        log(uiState.toData().toString())
        _response.postValue(uiState)
        //_searchResult.postValue(uiState.toData()?.results!!)

    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _response.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun <T> onItemClick(item: T) {
        log(item.toString())
    }
}

//private fun observeSearchQuery() {
//    disposable = searchQuerySubject
//        .debounce(300, TimeUnit.MILLISECONDS)
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(::onSearchQueryChange)
//}
//
//private fun onSearchQueryChange(query: String) {
//    val characters = response.value?.toData()?.results ?: emptyList()
//    if (query.isNotEmpty()) {
//        _searchResult.value = characters.filter {
//            it.name!!.contains(query, ignoreCase = true)
//        }
//    } else {
//        _searchResult.value = characters
//    }
//}
//
//fun search(query: String) {
//    searchQuerySubject.onNext(query)
//}
