package com.sophos.documentmanager_app.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.databinding.FragmentSendDocumentsViewBinding
import com.sophos.documentmanager_app.ui.viewmodel.SendDocumentsViewModel
import com.sophos.documentmanager_app.utils.Constants
import com.sophos.documentmanager_app.utils.Routing
import com.sophos.documentmanager_app.utils.TopBar
import com.sophos.documentmanager_app.utils.UserApp.Companion.prefs

class SendDocumentsView : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: SendDocumentsViewModel
    private lateinit var _binding: FragmentSendDocumentsViewBinding
    private val binding get() = _binding
    private var encodedImage: String? = null
    private lateinit var title: String

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[SendDocumentsViewModel::class.java]
        _binding = FragmentSendDocumentsViewBinding.inflate(inflater, container, false )

        val theme = prefs.getStoreTheme()
        if(theme == Constants.LIGHT_THEME){
            _binding.ivAddImage.setImageResource(R.drawable.add_photo)
        }else{
            _binding.ivAddImage.setImageResource(R.drawable.add_photo_dark)
        }

        setClickListener(activity as AppCompatActivity)
        viewModel.getOffice()

        val adapter =  ArrayAdapter(activity as AppCompatActivity, R.layout.documents_list, viewModel.documentsType)
        binding.dropdownMenuDocument.setAdapter(adapter)

        viewModel.mainCities.observe(viewLifecycleOwner){
                cities -> run{
            val cityAdapter =  ArrayAdapter(activity as AppCompatActivity, R.layout.documents_list, cities)
            binding.dropdownMenuCities.setAdapter(cityAdapter)
        }
        }

        viewModel.cameraAuth.observe(viewLifecycleOwner){
                data -> run{
            if(data!!.isAuth){
                takeImage()
            }
        }
        }

        viewModel.galleryAuth.observe(viewLifecycleOwner){
                data -> run{
            if(data!!.isAuth){
                selectImageFromGallery()
            }
        }
        }

        val email = prefs.getUserEmail()
        _binding.itEmail.setText(email)

        binding.btnSubmit.setOnClickListener {
            val image = encodedImage.toString()
            val description = _binding.itDescription.text.toString()
            val documentType = _binding.dropdownMenuDocument.text.toString()
            val documentId = _binding.itDocumentId.text.toString()
            val name = _binding.itName.text.toString()
            val lastname = _binding.itLastname.text.toString()
            val city = _binding.dropdownMenuCities.text.toString()

            viewModel.submitData(image, description, documentType, documentId, name, lastname, email, city, activity as AppCompatActivity)

            _binding.itDescription.setText("")
            _binding.ivAddImage.setImageDrawable(resources.getDrawable(R.drawable.add_photo))
            _binding.dropdownMenuDocument.setText("")
            _binding.itDocumentId.setText("")
            _binding.itName.setText("")
            _binding.itLastname.setText("")
            _binding.dropdownMenuCities.setText("")
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
        val language = prefs.getLanguage()
        title = if (language == "en") "Back" else "Regresar"
        TopBar()
            .show(activity as AppCompatActivity,toolbar,title, true)

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
    private fun takeImage(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
    }

    private fun selectImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val mActivity = activity

        if(resultCode == Activity.RESULT_OK){
            viewModel.imageAction(requestCode, data, mActivity).let{
                    imageData -> run{

                for(item in imageData){
                    encodedImage = item.code64
                    binding.ivAddImage.load(item.bitmap){
                        crossfade(true)
                        crossfade(1000)
                    }
                }
            }
            }
        }else{
            Toast.makeText(mActivity, "NO ACTION", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setClickListener(context: AppCompatActivity){
        binding.ivAddImage.setOnClickListener{viewModel.cameraCheckPermission(context)}
        binding.btnAddDocument.setOnClickListener{viewModel.galleryCheckPermission(context)}
    }
}