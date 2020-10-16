package com.example.quizletclone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.databinding.TermItemBinding

class TermListAdapter : ListAdapter<Term, TermListAdapter.ViewHolder>(TermListDiffUtilCallback()) {

    class ViewHolder(val binding: TermItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Term){
            binding.term = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TermItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }
}


class TermListDiffUtilCallback: DiffUtil.ItemCallback<Term>() {
    override fun areItemsTheSame(oldItem: Term, newItem: Term): Boolean {
        return oldItem.termId == newItem.termId
    }

    override fun areContentsTheSame(oldItem: Term, newItem: Term): Boolean {
        return oldItem == newItem
    }
}