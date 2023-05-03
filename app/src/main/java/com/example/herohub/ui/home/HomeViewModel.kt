package com.example.herohub.ui.home

import android.util.Log
import com.example.herohub.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    override val TAG: String
        get() = this::class.java.simpleName.toString()

    override fun log(message: String) {
        super.log(message)
        Log.e(TAG, message)
    }

    override fun onCleared() {
        super.onCleared()
    }
}