package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.data.repository.MarvelRepositoryImp
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val marvelRepositoryImp: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()
    private val seriesArgs = SeriesDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _seriesDetails = MutableLiveData<UiState<List<Series>?>>()
    val seriesDetails: LiveData<UiState<List<Series>?>>
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
            marvelRepositoryImp.getSeriesDetails(seriesArgs.seriesId),
            ::onSeriesSuccessData,
            ::onError
        )
    }

    private fun onSeriesSuccessData(seriesUiState: UiState<List<Series>?>) {
        _seriesDetails.value = seriesUiState
        seriesUiState.toData()?.let {
            _series.value = it.get(0)
        }
        _isFavorite.value = marvelRepositoryImp.isFavorite(series.value?.id.toString())
    }

    private fun onError(errorMessage: Throwable) {
        _seriesDetails.postValue(UiState.Error(errorMessage.message.toString()))
    }

    fun onFavClicked() {
        val favoriteItem = FavoriteItem(
            _series.value?.id.toString(),
            _series.value?.title.toString(),
            _series.value?.imageUrl.toString(),
            FavoriteItem.Type.SERIES
        )

        if (isFavorite.value == true) {
            marvelRepositoryImp.removeFavorite(favoriteItem)
            _isFavorite.postValue(false)
        } else {
            marvelRepositoryImp.addToFavorite(favoriteItem)
            _isFavorite.postValue(true)
        }
    }

}