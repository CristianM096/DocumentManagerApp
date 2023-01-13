package com.sophos.documentmanager_app.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentOfficeViewBinding
import com.sophos.documentmanager_app.ui.viewmodel.OfficeViewModel
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.TopBar
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs


class OfficeView : Fragment(), OnMapReadyCallback {
    private lateinit var _binding : FragmentOfficeViewBinding
    private val binding get() = _binding
    private lateinit var viewModel: OfficeViewModel
    private lateinit var map: GoogleMap
    private lateinit var title: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOfficeViewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[OfficeViewModel::class.java]

        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.toolbarContainer.toolbar
        val language = prefs.getLanguage()
        title = if (language == "en") "Go back" else "Regresar"
        TopBar().show(activity as AppCompatActivity, toolbar, title, true)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        viewModel.isLocationPermissionGranted(activity as AppCompatActivity, map)
        viewModel.getOfficesLocations(map)
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
        return Routing.navigations(activity as AppCompatActivity, item)
    }

}