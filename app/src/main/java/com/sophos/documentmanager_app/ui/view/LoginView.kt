package com.sophos.documentmanager_app.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.ViewModelProvider
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentLoginViewBinding
import com.sophos.documentmanager_app.ui.viewmodel.LoginViewModel
import com.sophos.documentmanager_app.utils.Constants
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs
import kotlin.math.log


class LoginView : Fragment() {
    private lateinit var email: String
    private lateinit var password: String

    private lateinit var _binding: FragmentLoginViewBinding
    private val binding get() = _binding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        _binding = FragmentLoginViewBinding.inflate(inflater, container, false)

        _binding.btnFingerprintLogin.setOnClickListener() {
            loginFingerprint()
        }
        _binding.btnLogin.setOnClickListener {
            loginCredentials()
        }
        validateTheme()
        return binding.root
    }

    private fun loginCredentials() {
        email = _binding.etLoginEmail.text.toString().trim()
        password = _binding.etLoginPassword.text.toString().trim()
        viewModel.login(email, password, activity as AppCompatActivity)
        viewModel.userData.observe(viewLifecycleOwner) { user ->
            run {
                if (user!!.access) {
                    Routing.goTo(activity as AppCompatActivity, HomeView())
                    Toast.makeText(activity, "Validado", Toast.LENGTH_SHORT).show()
                    Log.d("Login", "Logeado")
                } else {
                    Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    Log.d("Login", "NO Logeado")
                }
            }
        }
    }

    private fun loginFingerprint() {
        activity?.let { it -> viewModel.fingerPrintAuth(it) }
        viewModel.userAuth.observe(viewLifecycleOwner) { user ->
            run {
                if (user!!.auth) {
                    Routing.goTo(activity as AppCompatActivity, HomeView())
                    Toast.makeText(activity, "Validado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateTheme(){
        if( (prefs.getStoreTheme() == Constants.LIGHT_THEME)){
            _binding.ivLogo.setImageResource(R.drawable.sophos_day)
        }else{
            _binding.ivLogo.setImageResource(R.drawable.sophos_night)
        }
    }

}