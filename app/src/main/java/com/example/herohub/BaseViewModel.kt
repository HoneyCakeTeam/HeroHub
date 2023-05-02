package com.example.herohub

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    abstract val TAG: String
    protected fun CompositeDisposable.addToCompositeDisposable(disposable: Disposable) {
        this.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected open fun log(message: String) {
        Log.v(TAG, message)
    }
}