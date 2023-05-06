package com.example.herohub.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel : BaseViewModel(), SearchAdapter.SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()
    private val _response = MutableLiveData<UiState<DataResponse<Character>?>>()
    val response: LiveData<UiState<DataResponse<Character>?>>
        get() = _response

    val searchQuery = MutableLiveData<String>()
    private val searchQueryLiveData: LiveData<String>
        get() = searchQuery

    private val _searchResult = MediatorLiveData<List<Character>>()

    init {
        getAllCharacters()
        search()
    }

    private fun search() {
        _searchResult.addSource(searchQueryLiveData) { query ->
            log(query)
            /*if (query.isEmpty()) {
                _searchResult.value = _response.value?.toData()?.results!!
            } else {
                log(query)
                repository.searchQuery(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { response ->
                            _searchResult.value = response.toData()?.results!!
                        },
                        { error ->
                            log(error.message!!)
                        }
                    ).dispose()
            }*/
        }
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun getSearchResult(): LiveData<List<Character>> = _searchResult

    private fun getAllCharacters() {
        _response.postValue(UiState.Loading)
        disposeObservable(
            repository.getAllCharacters(),
            ::onGetCharacterSuccess, ::onGetCharacterFailure
        )
    }

    private fun onGetCharacterSuccess(uiState: UiState<DataResponse<Character>>) {
        _response.postValue(uiState)
        log(uiState.toData().toString())
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _response.postValue(UiState.Error(throwable.message.toString()))

    }

    override fun <T> onClickItem(item: T) {
        TODO("Not yet implemented")
    }
}

//    val searchResult: LiveData<List<Character>>
//        get() = _searchResult

//        repository.searchQuery(query)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { response ->
//                    _searchResult.value = response.toData()?.results!!
//                },
//                { error ->
//                    Log.e("SearchViewModel", "Search failed", error)
//                }
//            ).dispose()

//    private val _searchResult = MutableLiveData<List<Character>>()

//    private val _searchResults = MutableLiveData<List<Character>>()
//    val searchResults: LiveData<List<Character>> get() = _searchResults
//
//    // MutableLiveData for search query
//    private val _searchQuery = MutableLiveData<String>()
//    val searchQuery: LiveData<String> get() = _searchQuery
//
//    fun getAllCharacters() {
//        _response.postValue(UiState.Loading)
//        disposeObservable(repository.getAllCharacters(),
//            ::onGetCharacterSuccess , ::onGetCharacterFailure)
//    }
//
//    fun updateSearchQuery(query: String) {
//        _searchQuery.value = query
//    }
//
//    private fun getFilteredCharacters(query: String): Observable<List<Character>> {
//        return if (query.isNotEmpty()) {
//            repository.searchQuery(query)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map { it.toData()?.results!! }
//                .onErrorReturn { emptyList() }
//        } else {
//            repository.getAllCharacters()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map { it.toData()?.results!! }
//                .onErrorReturn { emptyList() }
//        }
//    }
//
//    init {
//        _searchQuery
//            .distinctUntilChanged()
//            .debounce(300, TimeUnit.MILLISECONDS)
//            .flatMapLatest { query ->
//                getFilteredCharacters(query)
//            }
//            .subscribe { results ->
//                _searchResults.value = results
//            }
//            .addTo(compositeDisposable)
//    }
//
////    init {
////        // observe the search query changes and execute the search
////        _searchQuery
////            .distinctUntilChanged()
////            .debounce(300, TimeUnit.MILLISECONDS)
////            .flatMapLatest { query ->
////                if (query.isNotEmpty()) {
////                    repository.searchQuery(query)
////                        .subscribeOn(Schedulers.io())
////                        .observeOn(AndroidSchedulers.mainThread())
////                        .map { it.toData()?.results!! }
////                        .onErrorReturn { emptyList() }
////                } else {
////                    // if the search query is empty, return all characters
////                    repository.getAllCharacters()
////                        .subscribeOn(Schedulers.io())
////                        .observeOn(AndroidSchedulers.mainThread())
////                        .map { it.toData()?.results!! }
////                        .onErrorReturn { emptyList() }
////                }
////            }
////            .subscribe { results ->
////                // filter characters based on search query
////                val filteredResults = if (_searchQuery.value.isNullOrEmpty()) {
////                    results
////                } else {
////                    results.filter { character ->
////                        character.name.contains(_searchQuery.value!!, ignoreCase = true)
////                    }
////                }
////                _searchResults.value = filteredResults
////            }
////            .addTo(compositeDisposable)
////    }
