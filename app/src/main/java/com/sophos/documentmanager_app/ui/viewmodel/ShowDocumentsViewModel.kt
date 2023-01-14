package com.sophos.documentmanager_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.sophosapp.data.model.rs_documents.DocumentsModel
import com.sophos.documentmanager_app.data.model.document.DocumentModel
import com.sophos.documentmanager_app.data.network.service.document.DocumentService
import com.sophos.documentmanager_app.utils.Communicator
import com.sophos.documentmanager_app.utils.NonSuccessResponse
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class ShowDocumentsViewModel : ViewModel() {
    private val _documents = MutableLiveData<DocumentsModel?>()
    val documents: LiveData<DocumentsModel?> = _documents

    fun getDocuments(){
        val email = prefs.getUserEmail()
        DocumentService().getDocumentsByEmail(email)
            .enqueue(object: Callback<DocumentsModel>{
                override fun onResponse(
                    call: Call<DocumentsModel>,
                    response: Response<DocumentsModel>
                ) {
                    if (response.isSuccessful){
                        _documents.postValue(response.body())

                    }else{
                        NonSuccessResponse().message(response.code())

                    }
                }
                override fun onFailure(call: Call<DocumentsModel>, t: Throwable) {
                    call.cancel()
                }
            }
        )
    }
    fun onDocumentSelected(document: DocumentModel, communicator: Communicator){
        communicator.passData(document.idRegister)
    }

}