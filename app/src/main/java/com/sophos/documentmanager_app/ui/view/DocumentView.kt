package com.sophos.documentmanager_app.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentDocumentViewBinding
import com.sophos.documentmanager_app.ui.viewmodel.DocumentViewModel
import com.sophos.documentmanager_app.utils.Constants
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.TopBar
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs

class DocumentView : Fragment() {

    private val viewModel: DocumentViewModel by viewModels()

    private lateinit var _binding: FragmentDocumentViewBinding
    private val binding get() = _binding

    private lateinit var idRegister: String
    private lateinit var title: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocumentViewBinding.inflate(inflater, container, false)
        idRegister = requireArguments().getString(Constants.ID_REGISTER,"")
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = _binding.toolbarContainer.toolbar
        val language = prefs.getLanguage()
        title = if (language == "en") "Back" else "Regresar"
        TopBar()
            .show(activity as AppCompatActivity, toolbar, title, true)

        viewModel.decodedImage.observe(viewLifecycleOwner){
                image -> _binding.detailImage.setImageBitmap(image)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
                currentState -> _binding.progressBar.isVisible = currentState
        }
        viewModel.getDetails(idRegister)
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navigation, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val title = prefs.getThemeTitle()
        menu.findItem(R.id.op_theme).title = title

        val language = prefs.getLanguageTitle()
        menu.findItem(R.id.op_language).title = language
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return Routing.navigations(activity as AppCompatActivity, item, true)
    }
}