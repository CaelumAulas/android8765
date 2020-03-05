package br.com.caelum.twittelum.app

import android.app.Application

class TwittelumApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance : TwittelumApp
        fun getInstance() : TwittelumApp {
            return instance
        }
    }
}
