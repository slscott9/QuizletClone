package com.example.quizletclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizletclone.data.domain.DomainFolder
import com.example.quizletclone.data.remote.responses.NetworkSet
import com.example.quizletclone.databinding.SetItemBinding

class SetListAdapter (val clickListener: SetListListener): ListAdapter<NetworkSet, SetListAdapter.ViewHolder>(SetListDiffUtilCallback()){
    class ViewHolder(val binding : SetItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkSet, clickListener: SetListListener){
            binding.networkSet = item
            binding.setListListerer = clickListener
            binding.executePendingBindings()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SetItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

class SetListListener(val clickListener: (id: Int) -> Unit){
    fun onClick(folder: NetworkSet) {
        clickListener(folder.folderId)
    }
}

class SetListDiffUtilCallback : DiffUtil.ItemCallback<NetworkSet>() {
    override fun areItemsTheSame(oldItem: NetworkSet, newItem: NetworkSet): Boolean {
        return oldItem.folderId == newItem.folderId
    }

    override fun areContentsTheSame(oldItem: NetworkSet, newItem: NetworkSet): Boolean {
        return oldItem == newItem
    }


}