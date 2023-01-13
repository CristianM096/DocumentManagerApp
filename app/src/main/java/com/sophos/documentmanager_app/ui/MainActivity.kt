package com.sophos.documentmanager_app.ui

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.ui.view.DocumentView
import com.sophos.documentmanager_app.ui.view.LoginView
import com.sophos.documentmanager_app.utils.Communicator
import com.sophos.documentmanager_app.utils.Constants
import com.sophos.documentmanager_app.utils.LanguageCtx
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs

class MainActivity : AppCompatActivity(), Communicator  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Routing.goTo(this, LoginView())
    }

    override fun passData(id: String) {
        val bundle = Bundle()
        bundle.putString(Constants.ID_REGISTER, id)
        val documentView = DocumentView()
        documentView.arguments = bundle
        Routing.goTo(this, documentView)
    }
    override fun attachBaseContext(newBase: Context) {

        val localeToSwitchTo = prefs.getLanguage()
        val localeUpdateContext: ContextWrapper = LanguageCtx.updateLanguage(newBase, localeToSwitchTo)
        super.attachBaseContext(localeUpdateContext)
    }

}