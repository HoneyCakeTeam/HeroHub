package com.example.herohub.ui.eventdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.Repository
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Event
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class EventDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    private val repository = Repository()
    override val TAG = "EVENT_DETAILS_VIEW_MODEL"

    private val eventArgs = EventDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _eventResponse = MutableLiveData<UiState<DataResponse<Event>>>()
    val eventResponse: LiveData<UiState<DataResponse<Event>>>
        get() = _eventResponse

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event>
        get() = _event

    init {
        getEvent()
    }

    private fun getEvent() {
        _eventResponse.postValue(UiState.Loading)
        disposeSingle(
            repository.getEvent(eventArgs.eventId),
            ::onGetEventSuccess,
            ::onGetEventFailure
        )

    }

    private fun onGetEventSuccess(uiState: UiState<DataResponse<Event>>) {
        _eventResponse.value = uiState
        uiState.toData()?.let {
            _event.value = it.results?.get(0)
        }
    }

    private fun onGetEventFailure(throwable: Throwable) {
        _eventResponse.value = UiState.Error(throwable.message.toString())
        log(throwable.message.toString())
    }
}


