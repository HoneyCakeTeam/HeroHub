package com.example.herohub

import android.app.Application


class HeroHub : Application() {

    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
    }

    companion object {
        var instance: HeroHub? = null
            private set
    }
}