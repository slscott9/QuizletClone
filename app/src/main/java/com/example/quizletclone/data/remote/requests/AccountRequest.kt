package com.example.quizletclone.data.remote.requests

data class AccountRequest(
    val email: String,
    val password: String,
    val userName: String
) {
}