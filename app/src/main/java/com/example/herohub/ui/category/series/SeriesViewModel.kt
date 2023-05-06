package com.example.herohub.ui.category.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class SeriesViewModel : BaseViewModel(), SeriesInteractionListener {
    override val TAG: String
        get() = this::class.java.simpleName
    private val repository = Repository()
    private val _allSeries = MutableLiveData<UiState<DataResponse<Series>>>()

    val allSeries: LiveData<UiState<DataResponse<Series>>>
        get() = _allSeries

    private val _seriesId = MutableLiveData<Int>()
    val seriesId: LiveData<Int>
        get() = _seriesId

    init {
        getAllSeries()
    }

    private fun getAllSeries() {
        disposeObservable(repository.getAllSeries(), ::onGetSeriesSuccess, ::onError)
    }

    private fun onGetSeriesSuccess(state: UiState<DataResponse<Series>>) {
        _allSeries.postValue(state)
    }

    private fun onError(errorMessage: Throwable) {
        _allSeries.postValue(UiState.Error(errorMessage.message.toString()))
    }

    override fun onClickItem(id: Int) {
        _seriesId.postValue(id)
    }

}