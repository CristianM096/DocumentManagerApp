package com.sophos.documentmanager_app.data.model.UserModel

import com.google.gson.annotations.SerializedName

data class UserModel (
    val id: String,
    @SerializedName("nombre") val name: String,
    @SerializedName("apellido") val lastName: String,
    @SerializedName("acceso") val access: Boolean,
    val admin: Boolean
)