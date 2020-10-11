package com.example.quizletclone.data.domain

data class DomainFolder(
    val name: String,
    val folderId: Int,
    val userEmail: String
)

data class DomainSet(
    val folderId: Int,
    val userEmail: String,
    val setName: String,
    val term: String,
    val answer: String
)