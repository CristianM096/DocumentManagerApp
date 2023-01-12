package com.sophos.documentmanager_app.ui.viewmodel

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sophos.documentmanager_app.data.model.UserModel.UserModel
import com.sophos.documentmanager_app.data.network.service.UserService
import com.sophos.documentmanager_app.utils.NonSuccessResponse
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class LoginViewModel : ViewModel(){
    private lateinit var executor: Executor
    private lateinit var biometricPromptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricPrompt: BiometricPrompt

    private val _userData = MutableLiveData<UserModel>()
    val userData : LiveData<UserModel> = _userData

//    private val _userAuth = MutableLiveData<UserAuth?>()
//    val userAuth: LiveData<UserAuth?> = _userAuth

    fun login(email: String, password:String, activity: AppCompatActivity){
        val validEmail = emailValidation(email)

        if (validEmail){
            UserService().login(email,password).enqueue(object : Callback<UserModel>{
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    println(response  )
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody!!.access){
                            _userData.postValue(responseBody!!)
                            prefs.storeUsername(responseBody.name)
                            prefs.storeUserEmail(email)
                        }else{
                            Toast.makeText(activity,"Invalid Credentials", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        NonSuccessResponse().message(response.code())
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    call.cancel()
                }
            }
            )
        }else{
            Toast.makeText(activity,"InvalidEmail",Toast.LENGTH_SHORT).show()
        }
    }
    private fun emailValidation(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}