package com.example.quizletclone.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.quizletclone.data.domain.DomainFolder

@BindingAdapter("setFolderName")
fun TextView.setFolderName(item: DomainFolder?){
    item?.name?.let {
        text = item.name
    }
}

@BindingAdapter("setUserName")
fun TextView.setUserName(item: DomainFolder?){
    item?.userName?.let {
        text = item.userName
    }
}


