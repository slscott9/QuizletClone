package com.example.quizletclone.data.domain

data class DomainFolder(
    val name: String,
    val folderId: Int,
    val userEmail: String,
    val userName: String,
    val description: String?
)

data class DomainSet(
    val setId: Int,
    val folderId: Int,
    val userEmail: String,
    val setName: String,
    val termCount: Int
)