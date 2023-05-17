package com.example.herohub.ui.eventdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.data.domain.model.Event
import com.example.herohub.data.domain.model.FavoriteItem
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState

class EventDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    private val marvelRepository = MarvelRepository()
    override val TAG = "EVENT_DETAILS_VIEW_MODEL"
    private val eventArgs = EventDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _eventResponse = MutableLiveData<UiState<DataResponse<Event>>>()
    val eventResponse: LiveData<UiState<DataResponse<Event>>>
        get() = _eventResponse

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event>
        get() = _event

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    init {
        getEvent()
    }

    private fun getEvent() {
        _eventResponse.postValue(UiState.Loading)
        disposeSingle(
            marvelRepository.getEvent(eventArgs.eventId),
            ::onGetEventSuccess,
            ::onGetEventFailure
        )

    }

    private fun onGetEventSuccess(uiState: UiState<DataResponse<Event>>) {
        _eventResponse.value = uiState
        uiState.toData()?.let {
            _event.value = it.results?.get(0)
        }
        _isFavorite.value = marvelRepository.isFavorite(event.value?.id.toString())
    }

    private fun onGetEventFailure(throwable: Throwable) {
        _eventResponse.value = UiState.Error(throwable.message.toString())
    }

    fun onFavClicked(){
        val favoriteItem = FavoriteItem(
            _event.value?.id.toString(),
            _event.value?.title.toString(),
            _event.value?.imageUrl.toString(),
            FavoriteItem.Type.EVENT
        )

        if(isFavorite.value == true) {
            marvelRepository.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepository.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }
}
