package com.sophos.documentmanager_app.utils

import android.annotation.SuppressLint
import android.app.Application

class UserApp: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var prefs : Preferences
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(applicationContext)
        prefs.setStoreTheme(Constants.LIGHT_THEME)
    }
}