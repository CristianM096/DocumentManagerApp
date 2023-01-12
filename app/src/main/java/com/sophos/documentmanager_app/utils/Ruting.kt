package com.sophos.documentmanager_app.utils

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.ui.view.HomeView
import com.sophos.documentmanager_app.ui.view.ShowDocumentsView

class Routing {
    companion object {
        fun navigation(context: AppCompatActivity, item: MenuItem, goBack: Boolean = false): Boolean {

            return when (item.itemId) {

                android.R.id.home -> if (goBack) goBack(context) else goTo(context, HomeView())

//                R.id.op_send_docs -> goTo(context, SendDocumentsFragment())

                R.id.op_see_docs -> goTo(context, ShowDocumentsView())

//                R.id.op_office -> goTo(context, OfficeFragment())

//                R.id.op_theme -> ThemeTool.toggleTheme(context)

//                R.id.op_language -> LanguageTool.toggleLanguage(context)

//                R.id.op_logout -> logout(context)

                else -> false
            }
        }

        fun goTo(context: AppCompatActivity, fragment: Fragment): Boolean {
            context.supportFragmentManager.commit {
                replace(R.id.view_container, fragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            return true
        }

        private fun goBack(context: AppCompatActivity): Boolean {
            context.supportFragmentManager.commit {
                context.onBackPressed()
            }
            return true
        }

        private fun logout(context: AppCompatActivity): Boolean {
            context.recreate()
            return true
        }
    }
}