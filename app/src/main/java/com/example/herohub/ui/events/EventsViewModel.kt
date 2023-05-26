package com.example.herohub.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Event
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
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
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllEvents().collect {
                withContext(Dispatchers.Main) {
                    onGetEvents(it)
                }
            }
        }
    }

    private fun onGetEvents(state: UiState<List<Event>>) {
        _events.postValue(state)
    }

    override fun onClickEvent(id: Int) {
        _eventClick.postValue(EventHandler(id))
    }

}