package com.example.quizletclone.data.remote.requests

import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm

data class AccountRequest(
    val email: String,
    val password: String,
    val userName: String
)


data class NewSetRequest(
    val set: NetworkSet,
    val termList : List<NetworkTerm>
)









