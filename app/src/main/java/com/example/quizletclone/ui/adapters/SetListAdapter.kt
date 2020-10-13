package com.example.quizletclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizletclone.data.domain.DomainFolder
import com.example.quizletclone.data.domain.DomainSet
import com.example.quizletclone.data.remote.responses.NetworkSet
import com.example.quizletclone.databinding.SetItemBinding

class SetListAdapter (val clickListener: SetListListener): ListAdapter<DomainSet, SetListAdapter.ViewHolder>(SetListDiffUtilCallback()){
    class ViewHolder(val binding : SetItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainSet, clickListener: SetListListener){
            binding.domainSet = item
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
    fun onClick(folder: DomainSet) {
        clickListener(folder.folderId)
    }
}

class SetListDiffUtilCallback : DiffUtil.ItemCallback<DomainSet>() {
    override fun areItemsTheSame(oldItem: DomainSet, newItem: DomainSet): Boolean {
        return oldItem.folderId == newItem.folderId
    }

    override fun areContentsTheSame(oldItem: DomainSet, newItem: DomainSet): Boolean {
        return oldItem == newItem
    }


}