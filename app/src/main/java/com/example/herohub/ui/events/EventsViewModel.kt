package com.example.herohub.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Event
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository
) : BaseViewModel(), EventsInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val _events = MutableLiveData<UiState<List<Event>>>()
    val events: LiveData<UiState<List<Event>>>
        get() = _events

    private val _eventClick = MutableLiveData<EventHandler<Int>>()
    val eventClick: LiveData<EventHandler<Int>>
        get() = _eventClick

    init {
        getAllEvents()
    }

    private fun getAllEvents() {
        _events.postValue(UiState.Loading)
/*
        disposeSingle(marvelRepositoryImp.getAllEvents(), ::onGetEventsSuccess, ::onError)
*/
    }

    private fun onGetEventsSuccess(state: UiState<List<Event>>) {
        _events.postValue(state)
    }

    private fun onError(throwable: Throwable) {
        _events.postValue(UiState.Error(throwable.message.toString()))
    }

    override fun onClickEvent(id: Int) {
        _eventClick.postValue(EventHandler(id))
    }

}