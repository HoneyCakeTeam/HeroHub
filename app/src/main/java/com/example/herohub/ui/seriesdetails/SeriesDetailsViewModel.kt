package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.Repository
import com.example.herohub.model.DataResponse
import com.example.herohub.model.FavoriteItem
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.UiState

class SeriesDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()
    private val seriesArgs = SeriesDetailsFragmentArgs.fromSavedStateHandle(state)

    private val repository: Repository by lazy { Repository() }

    private val _seriesDetails = MutableLiveData<UiState<DataResponse<Series>?>>()
    val seriesDetails: LiveData<UiState<DataResponse<Series>?>>
        get() = _seriesDetails

    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series>
        get() = _series
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    init {
        getSeriesDetails()
    }


    private fun getSeriesDetails() {
        disposeSingle(
            repository.getSeriesDetails(seriesArgs.seriesId),
            ::onSeriesSuccessData,
            ::onError
        )
    }

    private fun onSeriesSuccessData(seriesUiState: UiState<DataResponse<Series>?>) {
        _seriesDetails.value =seriesUiState
        seriesUiState.toData()?.let {
            _series.value = it.results?.get(0)
        }
        _isFavorite.value = repository.isFavorite(series.value?.id.toString())
    }

    private fun onError(errorMessage: Throwable) {
        _seriesDetails.postValue(UiState.Error(errorMessage.message.toString()))
    }
    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            _series.value?.id.toString(),
            _series.value?.title.toString(),
            _series.value?.thumbnail?.path.toString()
        )

        if (isFavorite.value == true) {
            repository.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            repository.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }


}