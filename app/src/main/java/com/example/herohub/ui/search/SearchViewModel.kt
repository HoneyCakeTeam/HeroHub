package com.example.herohub.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()

    private val _response = MutableLiveData<State<BaseResponse<Character>?>>()
    val response: LiveData<State<BaseResponse<Character>?>> get() = _response

     fun getAllCharacters() {
        _response.postValue(State.Loading)
        repository
            .getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _response.postValue(it)
                    log(it.toData().toString())
                },
                {
                    log(it.message.toString())
                }
            ).addToCompositeDisposable()
    }

    override fun <T> onItemClick(item: T) {

    }
}


