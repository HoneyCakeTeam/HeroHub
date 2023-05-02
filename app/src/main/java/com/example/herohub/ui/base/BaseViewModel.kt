package com.example.herohub.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    abstract val TAG: String
    fun Disposable.addToCompositeDisposable() {
        compositeDisposable.add(this)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected open fun log(message: String) {
        Log.v(TAG, message)
    }
}