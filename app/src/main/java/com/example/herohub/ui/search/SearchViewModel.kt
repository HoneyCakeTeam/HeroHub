package com.example.herohub.ui.search

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


class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()

    private val _response = MutableLiveData<UiState<DataResponse<Character>?>>()
    val response: LiveData<UiState<DataResponse<Character>?>> get() = _response

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery : LiveData<String>
        get() = _searchQuery

    private  val _searchResult = MediatorLiveData<List<Character>>()

    init {
        getAllCharacters()
        search()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    fun getSearchResult(): LiveData<List<Character>> = _searchResult

    private fun search() {
        _searchResult.addSource(_searchQuery) { query ->
            if (query.isEmpty()) {
                _searchResult.value = _response.value?.toData()?.results!!
            } else {
                repository.getAllCharacters()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { dataResponse ->
                        dataResponse.toData()?.results?.filter {
                            it.name!!.contains(query, ignoreCase = true)
                        } ?: emptyList()
                    }
                    .subscribe(
                        { filteredResults ->
                            _searchResult.value = filteredResults
                        },
                        { error ->
                            log(error.message!!)
                        }
                    ).dispose()
            }
        }
    }


    fun getAllCharacters() {
        _response.postValue(UiState.Loading)
        disposeObservable(repository.getAllCharacters(),
            ::onGetCharacterSuccess , ::onGetCharacterFailure)
    }

    private fun onGetCharacterSuccess(uiState: UiState<DataResponse<Character>>) {
        _response.postValue(uiState)
        log(uiState.toData().toString())
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _response.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun <T> onItemClick(item: T) {
    }
}


//    fun getSearchQuery(query: String) {
//        _response.postValue(UiState.Loading)
//        disposeObservable(repository.getSearchQuery(query),
//            ::onGetCharacterSuccess, ::onGetCharacterFailure)
//    }

