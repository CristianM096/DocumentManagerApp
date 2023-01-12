package com.sophos.documentmanager_app.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentHomeViewBinding
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.Routing.Companion.navigation
import com.sophos.documentmanager_app.utils.TopBar
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeView.newInstance] factory method to
 * create an instance of this fragment.
 */
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
//        _binding.btnSendDocuments.setOnClickListener{
//            Routing.goTo(activity as AppCompatActivity, SendDocumentsView())
//        }
//        _binding.btnSendDocuments.setOnClickListener{
//            Routing.goTo(activity as AppCompatActivity, OfficeView())
//        }
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
        inflater.inflate(R.menu.hamburger, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val theme = prefs.getThemeTitle()
        menu.findItem(R.id.op_theme).title = theme

        val language = prefs.getLanguageTitle()
        menu.findItem(R.id.op_language).title = language
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return Routing.navigation(activity as AppCompatActivity, item)
    }
}