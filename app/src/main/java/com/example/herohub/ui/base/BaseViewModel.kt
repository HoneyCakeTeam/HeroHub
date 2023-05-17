package com.example.herohub.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.herohub.data.source.remote.model.DataResponse
import com.example.herohub.ui.utils.UiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    abstract val TAG: String

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected open fun log(message: String) {
        Log.v(TAG, message)
    }

    fun <T : Any> disposeSingle(
        response: Single<UiState<DataResponse<T>>>,
        onSuccess: (data: UiState<DataResponse<T>>) -> Unit,
        onFailed: (e: Throwable) -> Unit,
    ) {
        compositeDisposable.add(
            response
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onFailed)
        )
    }
}