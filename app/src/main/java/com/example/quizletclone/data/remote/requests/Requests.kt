package com.example.quizletclone.data.remote.requests

data class AccountRequest(
    val email: String,
    val password: String,
    val userName: String
)


data class AddFolderRequest(
    val folderName: String,
    val userEmail: String,
    val description: String?

)

