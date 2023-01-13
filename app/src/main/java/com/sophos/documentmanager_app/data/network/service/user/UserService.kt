package com.sophos.documentmanager_app.data.network.service.user

import retrofit2.Call
import com.sophos.documentmanager_app.data.di.NetworkModule
import com.sophos.documentmanager_app.data.model.UserModel.UserModel
import javax.inject.Inject

class UserService @Inject constructor() {
    fun login(email:String, password:String): Call<UserModel>{
        return NetworkModule.getRestEngine().create(UserApiClient::class.java).login(email, password)
    }
}