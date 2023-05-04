package com.example.herohub.ui.storydetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.Story
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.core.Single

class StoryDetailsViewModel(private val repository:Repository) : BaseViewModel() {
    override val TAG: String = this::class.simpleName.toString()
    private val _storyDetailsData = MutableLiveData<Single<UiState<Story>>>()
    val storyDetailsData: LiveData<Single<UiState<Story>>>
        get() = _storyDetailsData


    fun getStoryDetails(id: String) {
        val response = repository.getStoryDetails(id)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe()
  //      _storyDetailsData.postValue(response)


    }
//    private fun onSuccess(){
//        _storyDetailsData.postValue(response)
//    }
}