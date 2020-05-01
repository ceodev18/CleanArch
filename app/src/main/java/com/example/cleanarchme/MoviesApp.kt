package com.example.cleanarchme

import android.app.Application
import com.antonioleiva.mymovies.initDI

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

}