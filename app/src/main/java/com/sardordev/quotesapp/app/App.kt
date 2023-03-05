package com.sardordev.quotesapp.app

import android.app.Application
import com.sardordev.quotesapp.data.db.DatabaseLiked
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseLiked.init(this)
    }
}