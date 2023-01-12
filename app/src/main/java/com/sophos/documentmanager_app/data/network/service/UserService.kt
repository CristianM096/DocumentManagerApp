package com.sophos.documentmanager_app.data.network.service

import retrofit2.Call
import com.sophos.documentmanager_app.data.di.NetwotkModule
import com.sophos.documentmanager_app.data.model.UserModel.UserModel

class UserService {
    fun login(email:String, password:String): Call<UserModel>{
        return NetwotkModule.getRestEngine().create(UserApiClient::class.java).login(email, password)
    }
}