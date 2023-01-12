package com.sophos.documentmanager_app.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sophos.documentmanager_app.data.model.UserModel.UserModel
import com.sophos.documentmanager_app.data.model.auth.Auth
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

    private val _userAuth = MutableLiveData<Auth?>()
    val userAuth: LiveData<Auth?> = _userAuth

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
    fun fingerPrintAuth(context: FragmentActivity){
        if (prefs.getUsername().isNotEmpty()){
            biometricPromptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Login")
                .setSubtitle("Login with your fingerprint")
                .setNegativeButtonText("Cancel")
                .build()
            executor = context.let { ContextCompat.getMainExecutor(context) }
            biometricPrompt = BiometricPrompt(context,executor,object :BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context, "Login with fingerprint Success", Toast.LENGTH_SHORT).show()
                    _userAuth.postValue(Auth(true))
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(context,errString.toString(),Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
                }
            })
            biometricPrompt.authenticate(biometricPromptInfo)
        }else{
            Toast.makeText(context,"First login with your credentials", Toast.LENGTH_SHORT).show()
        }
    }


}