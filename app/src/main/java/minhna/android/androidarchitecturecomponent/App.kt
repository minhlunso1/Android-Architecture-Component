package minhna.android.androidarchitecturecomponent

import android.app.Application

/**
 ** Created by Minh on 6/25/2018
 **/

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}