package com.example.veritygithubinsight

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.i("Lucasteste", "onCreate: myapplication")
    }
}