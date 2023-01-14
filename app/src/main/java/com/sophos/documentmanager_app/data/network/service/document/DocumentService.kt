package com.sophos.documentmanager_app.data.network.service.document

import com.kotlin.sophosapp.data.model.rs_documents.DocumentsModel
import com.sophos.documentmanager_app.data.di.NetworkModule
import com.sophos.documentmanager_app.data.model.office.DocumentSendModel
import retrofit2.Call

class DocumentService {

    private val service = NetworkModule.getRestEngine().create(DocumentApiClient::class.java)

    fun getDocumentsByEmail(email: String): Call<DocumentsModel> {
        return service.getDocumentsByEmail(email)
    }

    fun getDocumentById(idRegister: String): Call<DocumentsModel> {
        return service.getDocumentById(idRegister)
    }
    fun sendDocument(data: DocumentSendModel): Call<DocumentSendModel> {
        return service.sendDocument(data)
    }
}