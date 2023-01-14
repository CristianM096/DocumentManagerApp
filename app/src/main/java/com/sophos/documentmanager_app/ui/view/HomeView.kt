package com.sophos.documentmanager_app.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentHomeViewBinding
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.TopBar
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs


class HomeView : Fragment() {
    private lateinit var _binding: FragmentHomeViewBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeViewBinding.inflate(inflater,container, false)
        _binding.btnSeeDocuments.setOnClickListener{
            Routing.goTo(activity as AppCompatActivity, ShowDocumentsView())
        }
        _binding.btnSendDocuments.setOnClickListener{
            Routing.goTo(activity as AppCompatActivity, SendDocumentsView())
        }
        _binding.btnOffice.setOnClickListener{
            Routing.goTo(activity as AppCompatActivity, OfficeView())
        }
    return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarContainer.toolbar
        val userName = prefs.getUsername()
        TopBar().show(activity as AppCompatActivity, toolbar, userName, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val theme = prefs.getThemeTitle()
        menu.findItem(R.id.op_theme).title = theme

        val language = prefs.getLanguageTitle()
        menu.findItem(R.id.op_language).title = language
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return Routing.navigations(activity as AppCompatActivity, item)
    }
}