package com.example.myspacex

import android.app.Application
import com.example.myspacex.di.DI

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.initialize(this)
    }
}