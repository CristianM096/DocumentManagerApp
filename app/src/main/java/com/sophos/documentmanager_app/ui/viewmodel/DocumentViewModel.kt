package com.sophos.documentmanager_app.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.sophosapp.data.model.rs_documents.DocumentsModel
import com.sophos.documentmanager_app.data.network.service.document.DocumentService
import com.sophos.documentmanager_app.utils.ImageTools
import com.sophos.documentmanager_app.utils.NonSuccessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class DocumentViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _decodedImage = MutableLiveData<Bitmap?>()
    val decodedImage: LiveData<Bitmap?> = _decodedImage

    fun getDetails(idRegister: String){
        _isLoading.postValue(true)
        DocumentService().getDocumentById(idRegister).enqueue(object: Callback<DocumentsModel> {

            override fun onResponse(call: Call<DocumentsModel>, response: Response<DocumentsModel>) {
                if(response.isSuccessful){
                    Log.e("Error", "Peticion correcta")
                    _isLoading.postValue(false)
                    val responseBody = response.body()
                    responseBody!!.Items.forEach { item ->
                        val image = ImageTools.decodeImage(item.file)
                        _decodedImage.postValue(image)
                    }
                }else{
                    Log.e("Error", "Peticion NO correcta")
                    NonSuccessResponse().message(response.code())
                }
            }
            override fun onFailure(call: Call<DocumentsModel>, t: Throwable) {
                call.cancel()
            }
        })
    }
}