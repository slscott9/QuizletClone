package com.example.quizletclone.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.quizletclone.data.domain.DomainFolder
import com.example.quizletclone.data.remote.responses.NetworkSet

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

@BindingAdapter("setName")
fun TextView.setName(item : NetworkSet?){
    item?.setName?.let {
        text = item.setName
    }
}




