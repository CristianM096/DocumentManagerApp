package com.sophos.documentmanager_app.data.network.service.user

import com.sophos.documentmanager_app.data.model.user.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {
    @GET("RS_Usuarios")
    fun login(@Query("idUsuario") email: String,@Query("clave") password:String) : Call<UserModel>
}