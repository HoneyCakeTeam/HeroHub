package com.example.herohub.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Event
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class EventsViewModel : BaseViewModel(), EventsInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val repository = Repository()

    private val _events = MutableLiveData<UiState<DataResponse<Event>>>()
    val events: LiveData<UiState<DataResponse<Event>>>
        get() = _events
    private val _eventId = MutableLiveData<Int>()
    val eventId: LiveData<Int>
        get() = _eventId

    init {
        getAllEvents()
    }

    private fun getAllEvents() {
        _events.postValue(UiState.Loading)
        disposeObservable(repository.getAllEvents(), ::onGetEventsSuccess, ::onError)
    }

    private fun onGetEventsSuccess(state: UiState<DataResponse<Event>>) {
        _events.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _events.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickItem(id: Int) {
        _eventId.postValue(id)
    }

}