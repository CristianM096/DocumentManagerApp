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
    fun getUsername(): String {
        return storage.getString(Constants.SHARED_USER_NAME, "")!!
    }
    fun getUserEmail(): String {
        return storage.getString(Constants.SHARED_USER_EMAIL, "")!!
    }
    fun storeThemeTitle(title: String) {
        storage.edit().putString(Constants.MAIN_THEME_TITLE, title).apply()
    }
    fun getThemeTitle(): String {
        return storage.getString(Constants.MAIN_THEME_TITLE, "Modo nocturno")!!
    }
    fun getLanguageTitle(): String {
        return storage.getString(Constants.MAIN_LANGUAGE_TITLE, "Idioma ingles")!!
    }
    fun storeLanguageTitle(title: String) {
        storage.edit().putString(Constants.MAIN_LANGUAGE_TITLE, title).apply()
    }
    ///



}