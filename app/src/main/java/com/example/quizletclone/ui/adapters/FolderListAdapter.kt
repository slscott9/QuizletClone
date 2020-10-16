package com.example.quizletclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizletclone.data.dto.DomainFolder
import com.example.quizletclone.databinding.FolderListItemBinding

class FolderListAdapter (val clickListener: FolderListListener): ListAdapter<DomainFolder, FolderListAdapter.ViewHolder>(FolderDiffUtilCallback()){
    class ViewHolder(val binding : FolderListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainFolder, clickListener: FolderListListener){
            binding.folder = item
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FolderListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

class FolderListListener(val clickListener: (id: Long) -> Unit){
    fun onClick(folder: DomainFolder) {
        clickListener(folder.folderId)
    }
}

class FolderDiffUtilCallback : DiffUtil.ItemCallback<DomainFolder>() {
    override fun areItemsTheSame(oldItem: DomainFolder, newItem: DomainFolder): Boolean {
        return oldItem.folderId == newItem.folderId
    }

    override fun areContentsTheSame(oldItem: DomainFolder, newItem: DomainFolder): Boolean {
        return oldItem == newItem
    }


}