package com.example.herohub.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }

    private val _characterResponse = MutableLiveData<State<Character>>()
    val characterResponse: LiveData<State<Character>>
        get() = _characterResponse

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _characterResponse.postValue(State.Loading)

        repository.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onGetCharacterSuccess, ::onGetCharacterError)
            .addToCompositeDisposable()
    }

    private fun onGetCharacterSuccess(state: State<Character>) =
        _characterResponse.postValue(state)

    private fun onGetCharacterError(errorMessage: Throwable) {
        _characterResponse.postValue(State.Error(errorMessage.message.toString()))
        log(errorMessage.message.toString())
    }
}