package com.sophos.documentmanager_app.utils.adapter

import android.content.DialogInterface.OnClickListener
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sophos.documentmanager_app.data.model.document.DocumentModel
import com.sophos.documentmanager_app.databinding.DocumentBinding

class DocumentsViewHolder (view: View): RecyclerView.ViewHolder(view){
    private var binding = DocumentBinding.bind(itemView)

    fun render(document: DocumentModel, onClickListener: (DocumentModel) -> Unit){
        binding.tvName.text = document.name
        binding.tvDate.text = document.date.take(10)
        binding.tvDescription.text = document.fileType.take(10)

        itemView.setOnClickListener{
            onClickListener(document)
        }
    }
}