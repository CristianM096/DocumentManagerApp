package com.sophos.documentmanager_app.data.network.service.document

import com.kotlin.sophosapp.data.model.rs_documents.DocumentsModel
import retrofit2.Call
import retrofit2.http.GET
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
}