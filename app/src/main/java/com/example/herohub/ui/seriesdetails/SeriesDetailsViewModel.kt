package com.example.herohub.ui.seriesdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.herohub.data.Repository
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesDetailsViewModel : BaseViewModel() {
    override val TAG: String
        get() = this::class.java.simpleName.toString()

    private val repository: Repository by lazy { Repository() }

    private val _seriesData = MutableLiveData<State<BaseResponse<Series>?>>()
    val seriesData: LiveData<State<BaseResponse<Series>?>>
        get() = _seriesData

    init {
        getAllSeriesList()
    }

    private fun getAllSeriesList() {
        _seriesData.postValue(State.Loading)

        repository.getAllSeries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSeriesSuccessData, ::onError)
            .addToCompositeDisposable()
    }

    private fun onSeriesSuccessData(seriesState: State<BaseResponse<Series>?>) {
        _seriesData.postValue(seriesState)
        log(seriesState.toData().toString())
    }

    private fun onError(errorMessage: Throwable) {
        log(errorMessage.message.toString())
    }


}