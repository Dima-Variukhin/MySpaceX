package com.example.myspacex.di

import android.app.Application

object DI {

    sealed class Config {
        object RELEASE : Config()
        object TEST : Config()
    }

    fun initialize(app: Application, configuration: Config = Config.RELEASE) {
        NetworkDI.initialize()
        MainScreen.initialize(app, configuration)
    }
}