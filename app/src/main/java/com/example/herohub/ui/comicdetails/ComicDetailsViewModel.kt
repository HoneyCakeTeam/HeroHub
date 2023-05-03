package com.example.herohub.ui.comicdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Comic
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ComicDetailsViewModel : BaseViewModel() {
    override val TAG = "ComicDetailsViewModel"
    private val repository = Repository()
    private val _comicResponse = MutableLiveData<State<BaseResponse<Comic>>>()
    val comicResponse: LiveData<State<BaseResponse<Comic>>>
        get() = _comicResponse

    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic>
        get() = _comic


    fun getComic(comicId: Int) {
        _comicResponse.postValue(State.Loading)
        repository.getComic(comicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::onGetComicSuccess, ::onGetComicFailure
            ).addToCompositeDisposable()
    }

    private fun onGetComicSuccess(state: State<BaseResponse<Comic>>) {
        _comicResponse.value = state
        state.toData()?.let {
            _comic.value = it.data.results?.get(0)
        }
    }

    private fun onGetComicFailure(throwable: Throwable) {
        _comicResponse.value = State.Error(throwable.message.toString())
        log(throwable.message.toString())
    }
}