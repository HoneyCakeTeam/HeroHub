package com.example.herohub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }

    private val _characterResponse = MutableLiveData<UiState<BaseResponse<Character>?>>()
    val characterResponse: LiveData<UiState<BaseResponse<Character>?>>
        get() = _characterResponse

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _characterResponse.postValue(UiState.Loading)

        repository.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetCharacterSuccess, ::onGetCharacterError)
            .addToCompositeDisposable()
    }

    private fun onGetCharacterSuccess(UiState: UiState<BaseResponse<Character>?>) {
        _characterResponse.postValue(UiState)
        log(UiState.toData().toString())
    }

    private fun onGetCharacterError(errorMessage: Throwable) =
        log(errorMessage.message.toString())
}