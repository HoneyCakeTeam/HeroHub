package com.example.herohub.ui.category.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Comic
import com.example.herohub.model.DataResponse
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class ComicsViewModel : BaseViewModel(), ComicInteractionListener {
    override val TAG: String = this::class.java.simpleName.toString()
    private val repository = Repository()
    private val _comics = MutableLiveData<UiState<DataResponse<Comic>>>()
    val comics: LiveData<UiState<DataResponse<Comic>>>
        get() = _comics

    private fun getAllComics() {
        disposeObservable(repository.getAllComics(), ::onGetComicsSuccess, ::onError)
    }

    private fun onGetComicsSuccess(state: UiState<DataResponse<Comic>>) {
        _comics.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        log("Error : ${throwable.message.toString()}")
    }

    override fun onClickItem(id: Int) {
        log("$id clicked")
    }
}