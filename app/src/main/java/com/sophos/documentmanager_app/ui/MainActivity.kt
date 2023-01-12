package com.sophos.documentmanager_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.ui.view.LoginView
import com.sophos.documentmanager_app.utils.Communicator
import com.sophos.documentmanager_app.utils.Routing

class MainActivity : AppCompatActivity(), Communicator  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Routing.goTo(this, LoginView())
    }

}