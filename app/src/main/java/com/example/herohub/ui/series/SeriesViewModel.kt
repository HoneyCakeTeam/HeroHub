package com.example.herohub.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.EventHandler
import com.example.herohub.ui.utils.UiState

class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val marvelRepositoryImp = MarvelRepositoryImp()

    private val _allSeries = MutableLiveData<UiState<List<Series>>>()
    val allSeries: LiveData<UiState<List<Series>>>
        get() = _allSeries

    private val _seriesClick = MutableLiveData<EventHandler<Int>>()
    val seriesClick: LiveData<EventHandler<Int>>
        get() = _seriesClick

    init {
        getAllSeries()
    }

    private fun getAllSeries() {
        _allSeries.postValue(UiState.Loading)
        disposeSingle(marvelRepositoryImp.getAllSeries(), ::onGetSeriesSuccess, ::onError)
    }

    private fun onGetSeriesSuccess(state: UiState<List<Series>>) {
        _allSeries.postValue(state)
    }

    private fun onError(errorMessage: Throwable) {
        _allSeries.postValue(UiState.Error(errorMessage.message.toString()))
    }

    override fun onClickSeries(id: Int) {
        _seriesClick.postValue(EventHandler(id))
    }

}