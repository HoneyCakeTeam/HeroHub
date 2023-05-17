package com.example.herohub.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domin.model.DataResponse
import com.example.herohub.domin.model.Event
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState

class EventsViewModel : BaseViewModel(), EventsInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val marvelRepository = MarvelRepository()

    private val _events = MutableLiveData<UiState<DataResponse<Event>>>()
    val events: LiveData<UiState<DataResponse<Event>>>
        get() = _events

    private val _eventClick = MutableLiveData<EventHandler<Int>>()
    val eventClick: LiveData<EventHandler<Int>>
        get() = _eventClick

    init {
        getAllEvents()
    }

    private fun getAllEvents() {
        _events.postValue(UiState.Loading)
        disposeSingle(marvelRepository.getAllEvents(), ::onGetEventsSuccess, ::onError)
    }

    private fun onGetEventsSuccess(state: UiState<DataResponse<Event>>) {
        _events.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _events.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickEvent(id: Int) {
        _eventClick.postValue(EventHandler(id))
    }

}