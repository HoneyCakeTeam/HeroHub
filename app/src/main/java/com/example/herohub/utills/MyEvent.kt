package com.example.herohub.utills

import androidx.lifecycle.Observer

open class MyEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserve<T>(private val onEventUnhandledContent:(T) ->Unit)
    : Observer<MyEvent<T>?> {
    override fun onChanged(event: MyEvent<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}