package com.sophos.documentmanager_app.data.model.document

import com.google.gson.annotations.SerializedName

data class DocumentModel (
    @SerializedName("Ciudad") val city: String,
    @SerializedName("Fecha")  val date: String,
    @SerializedName("TipoAdjunto")  val fileType: String,
    @SerializedName("Nombre") val name: String,
    @SerializedName("Apellido") val lastName: String,
    @SerializedName("Identificacion") val id: String,
    @SerializedName("IdRegistro") val idRegister: String,
    @SerializedName("TipoId") val idType: String,
    @SerializedName("Correo") val email: String,
    @SerializedName("Adjunto") val file: String
)