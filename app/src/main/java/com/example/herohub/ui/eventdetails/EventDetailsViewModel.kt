package com.example.herohub.ui.eventdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Event
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class EventDetailsViewModel : BaseViewModel() {
    private val repository = Repository()
    override val TAG = "EVENT_DETAILS_VIEW_MODEL"

    private val _eventResponse = MutableLiveData<State<BaseResponse<Event>>>()
    val eventResponse: LiveData<State<BaseResponse<Event>>>
        get() = _eventResponse

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event>
        get() = _event
    fun getEvent(eventId: Int) {
        _eventResponse.postValue(State.Loading)
        repository.getEvent(eventId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::onGetEventSuccess,
                ::onGetEventFailure
            ).addToCompositeDisposable()

    }

    private fun onGetEventSuccess(state: State<BaseResponse<Event>>) {
        _eventResponse.value = state
        state.toData()?.let {
            _event.value = it.data.results?.get(0)
        }
    }

    private fun onGetEventFailure(throwable: Throwable) {
        _eventResponse.value = State.Error(throwable.message.toString())
        log(throwable.message.toString())
    }
}


