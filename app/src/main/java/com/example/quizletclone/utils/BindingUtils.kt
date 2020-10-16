package com.example.quizletclone.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.quizletclone.data.dto.DomainFolder
import com.example.quizletclone.data.dto.DomainSet
import com.example.quizletclone.data.entities.Term

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
fun TextView.setName(item : DomainSet?){
    item?.setName?.let {
        text = item.setName
    }
}


@BindingAdapter("setTermCount")
fun TextView.setTermCount(item : DomainSet?){
    item?.termCount?.let {
        text = item.termCount.toString()
    }
}

@BindingAdapter("setUserName")
fun TextView.setUserName(item : DomainSet?){
    item?.userEmail?.let {
        text = item.userEmail
    }
}

@BindingAdapter("setTermQuestion")
fun TextView.setTermQuestion(item : Term?){
    item?.question?.let{
        text = item.question
    }
}

@BindingAdapter("setTermAnswer")
fun TextView.setTermAnswer(item : Term?){
    item?.answer?.let{
        text = item.answer
    }
}




