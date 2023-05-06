package com.example.herohub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel(), SearchAdapter.SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()

    private val _response = MutableLiveData<UiState<DataResponse<Character>?>>()
    val response: LiveData<UiState<DataResponse<Character>?>> get() = _response

    fun getAllCharacters() {
        _response.postValue(UiState.Loading)
        disposeObservable(repository.getAllCharacters(),
            ::onGetCharacterSuccess , ::onGetCharacterFailure)
    }

    fun searchQuery(name: String) {
        _response.postValue(UiState.Loading)
        disposeObservable(repository.searchQuery(name),
            ::onGetCharacterSuccess, ::onGetCharacterFailure)
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


