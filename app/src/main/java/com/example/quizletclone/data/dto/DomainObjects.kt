package com.example.quizletclone.data.dto

import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import java.util.*

data class DomainFolder(
    val name: String,
    val folderId: Long,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val timeStamp: Long
)

data class DomainSet(
    val setId: Long,
    val folderId: Long?,
    val userEmail: String,
    val setName: String,
    val termCount: Int = 0,
    val timeStamp: Long

)


data class DomainTerm(
    val termId: Long,
    val setId: Long,
    val term: String,
    val answer: String,
    val timeStamp: Long

)

