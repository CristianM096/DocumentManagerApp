package com.sophos.documentmanager_app.data.model.office

import com.google.gson.annotations.SerializedName

data class OfficeModel(
    @SerializedName("Ciudad") val cityName: String,
    @SerializedName("IdOficina") val officeId: Int,
    @SerializedName("Latitud") val latitude: String,
    @SerializedName("Longitud") val longitude: String,
    @SerializedName("Nombre") val placeName: String
)

