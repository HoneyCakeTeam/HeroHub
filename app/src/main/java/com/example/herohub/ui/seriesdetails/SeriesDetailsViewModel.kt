package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class SeriesDetailsViewModel : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }

    private val _seriesData = MutableLiveData<UiState<DataResponse<Series>?>>()
    val seriesData: LiveData<UiState<DataResponse<Series>?>>
        get() = _seriesData

    init {
        getAllSeriesList()
    }

    private fun getAllSeriesList() {
        _seriesData.postValue(UiState.Loading)
        disposeObservable(repository.getAllSeries(), ::onSeriesSuccessData, ::onError)
    }

    private fun onSeriesSuccessData(seriesUiState: UiState<DataResponse<Series>?>) {
        _seriesData.postValue(seriesUiState)
        log(seriesUiState.toData().toString())
    }

    private fun onError(errorMessage: Throwable) {
        _seriesData.postValue(UiState.Error(errorMessage.message.toString()))
    }


}