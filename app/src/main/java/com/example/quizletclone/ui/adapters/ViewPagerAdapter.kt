package com.example.quizletclone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizletclone.R
import kotlinx.android.synthetic.main.item_page.view.*

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PageVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageVH {
       return  PageVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun onBindViewHolder(holder: PageVH, position: Int) = holder.itemView.run {

        when(position){
            0 -> {
                imageView.setImageResource(R.drawable.quizlet_logo)
                imageView.setBackgroundResource(R.color.trans)
            }
            1 -> imageView.setImageResource(R.drawable.quizlet_search)
            2 -> imageView.setImageResource(R.drawable.quizlet_logos)
        }
    }

    override fun getItemCount(): Int  = 3

    class PageVH(itemView: View) : RecyclerView.ViewHolder(itemView)


}