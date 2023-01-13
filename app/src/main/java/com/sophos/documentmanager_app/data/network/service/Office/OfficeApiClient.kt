package com.sophos.documentmanager_app.data.network.service.Office

import com.sophos.documentmanager_app.data.model.office.OfficesModel
import retrofit2.Call
import retrofit2.http.GET

interface OfficeApiClient {
    @GET("RS_Oficinas")
    fun getOffice(): Call<OfficesModel>
}