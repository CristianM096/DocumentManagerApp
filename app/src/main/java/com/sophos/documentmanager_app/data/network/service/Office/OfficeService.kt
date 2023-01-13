package com.sophos.documentmanager_app.data.network.service.Office

import com.sophos.documentmanager_app.data.di.NetworkModule
import com.sophos.documentmanager_app.data.model.office.OfficesModel
import retrofit2.Call

class OfficeService {
    fun getOffice(): Call<OfficesModel> {
        return NetworkModule.getRestEngine().create(OfficeApiClient::class.java).getOffice()
    }
}