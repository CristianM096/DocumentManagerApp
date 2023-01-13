package com.sophos.documentmanager_app.utils.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.sophosapp.data.model.rs_documents.DocumentsModel
import com.sophos.documentmanager_app.R
import com.sophos.documentmanager_app.data.model.document.DocumentModel

class DocumentsAdapter (
    private var documentsList: DocumentsModel,
    private val onClickListener: (DocumentModel) -> Unit
) : RecyclerView.Adapter<DocumentsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.document, parent, false)
        return DocumentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocumentsViewHolder, position: Int) {
        val document = documentsList.Items[position]
        holder.render(document,onClickListener)
    }

    override fun getItemCount(): Int = documentsList.Items.size

}