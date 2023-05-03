package com.example.herohub.ui.seriesdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.herohub.data.Repository
import com.example.herohub.model.Series
import com.example.herohub.utills.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesDetailsViewModel :ViewModel(){

    private val repository = Repository()
    private val _data = MutableLiveData<State<Series>>()
    val data: LiveData<State<Series>>
        get() = _data

    init {
        //  _counter.value = Counter(0)
        getAllSerieslist()
    }
//
//    fun incrementCounter() {
//        val currentCount = _counter.value?.count ?: 0
//        _counter.postValue(Counter(currentCount + 1))
//    }

    fun getAllSerieslist(): Disposable {
       return repository.getAllSeries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onPostData, ::onError)
    }

    private fun onPostData(series:State<Series>) {
        _data.postValue(series)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "Error fetching Series")
    }

    companion object {
        private const val TAG = "SeriesDetailsViewModel"
    }
}