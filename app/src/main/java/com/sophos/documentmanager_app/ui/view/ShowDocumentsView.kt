package com.sophos.documentmanager_app.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentShowDocumentsViewBinding
import androidx.recyclerview.widget.RecyclerView
import com.sophos.documentmanager_app.ui.viewmodel.ShowDocumentsViewModel
import com.sophos.documentmanager_app.utils.Communicator
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.TopBar
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs
import com.sophos.documentmanager_app.utils.adapter.DocumentsAdapter

class ShowDocumentsView : Fragment() {

    private lateinit var viewModel: ShowDocumentsViewModel
    private lateinit var _binding: FragmentShowDocumentsViewBinding
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView
    private lateinit var documentAdapter: DocumentsAdapter
    private lateinit var communicator: Communicator
    private lateinit var title: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowDocumentsViewBinding.inflate(inflater,container, false)
        viewModel = ViewModelProvider(this)[ShowDocumentsViewModel::class.java]
        communicator = activity as Communicator
        viewModel.getDocuments()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val language = prefs.getLanguage()
        title = if (language == "en") "Go back" else "Regresar"
        val topbar = _binding.toolbarContainer.toolbar
        TopBar().show(activity as AppCompatActivity,topbar,title, true)
        recyclerView = _binding.docsRecyclerView
        viewModel.documents.observe(viewLifecycleOwner){
            documents -> run {
            _binding.loadingSchema.isVisible = false
            _binding.docsRecyclerView.isVisible = true
            recyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                documentAdapter = DocumentsAdapter(documents!!){
                    document -> viewModel.onDocumentSelected(document,communicator)
                }
                recyclerView.adapter = documentAdapter
            }
        }
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return Routing.navigations(activity as AppCompatActivity, item, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation,menu)
        super.onCreateOptionsMenu(menu, inflater)
        val title = prefs.getThemeTitle()
        menu.findItem(R.id.op_theme).title = title
        val language = prefs.getLanguageTitle()
        menu.findItem(R.id.op_language).title = language
    }
}