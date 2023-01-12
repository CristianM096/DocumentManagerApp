package com.sophos.documentmanager_app.utils

import android.content.Context

class Preferences (val context: Context){
    val storage = context.getSharedPreferences(Constants.STORAGE, 0)
    fun getStoreTheme(): String {
        return storage.getString(Constants.MAIN_THEME, Constants.LIGHT_THEME)!!
    }
    fun setStoreTheme(theme: String) {
        storage.edit().putString(Constants.MAIN_THEME, theme).apply()
    }
    fun storeUsername(userName: String) {
        storage.edit().putString(Constants.SHARED_USER_NAME, userName).apply()
    }
    fun storeUserEmail(userEmail: String ){
        storage.edit().putString(Constants.SHARED_USER_EMAIL, userEmail).apply()
    }

    ///



}