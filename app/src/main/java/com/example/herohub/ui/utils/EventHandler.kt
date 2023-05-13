package com.example.herohub.ui.utils

import androidx.lifecycle.Observer

open class EventHandler<out T>(private val content: T) {
    var hasBeenHandled: Boolean = false
        private set

    fun getContentIfHandled(): T? {
        return if (hasBeenHandled)
            null
        else {
            hasBeenHandled = true
            content
        }
    }
}

class EventObserve<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<EventHandler<T>?> {

    override fun onChanged(value: EventHandler<T>?) {
        value?.getContentIfHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}