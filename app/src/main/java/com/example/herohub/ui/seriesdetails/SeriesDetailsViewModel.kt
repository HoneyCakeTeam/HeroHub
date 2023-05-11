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

    private val _seriesDetails = MutableLiveData<UiState<DataResponse<Series>?>>()
    val seriesDetails: LiveData<UiState<DataResponse<Series>?>>
        get() = _seriesDetails


    fun getSeriesDetails(seriesId:Int) {
        disposeSingle(repository.getSeriesDetails(seriesId), ::onSeriesSuccessData, ::onError)
    }

    private fun onSeriesSuccessData(seriesUiState: UiState<DataResponse<Series>?>) {
        _seriesDetails.postValue(seriesUiState)
    }

    private fun onError(errorMessage: Throwable) {
        _seriesDetails.postValue(UiState.Error(errorMessage.message.toString()))
    }


}