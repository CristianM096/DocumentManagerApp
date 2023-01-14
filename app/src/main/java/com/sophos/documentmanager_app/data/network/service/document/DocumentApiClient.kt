package com.sophos.documentmanager_app.data.network.service.document

import com.kotlin.sophosapp.data.model.rs_documents.DocumentsModel
import com.sophos.documentmanager_app.data.model.office.DocumentSendModel
import com.sophos.documentmanager_app.data.model.office.OfficeModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DocumentApiClient {
    @GET("RS_Documentos")
    fun getDocumentsByEmail(
        @Query("correo") email: String
    ): Call<DocumentsModel>


    @GET("RS_Documentos")
    fun getDocumentById(
        @Query("idRegistro") idRegister: String
    ): Call<DocumentsModel>

    @POST("RS_Documentos")
    fun sendDocument(@Body document: DocumentSendModel): Call<DocumentSendModel>
}