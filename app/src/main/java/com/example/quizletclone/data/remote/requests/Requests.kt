package com.example.quizletclone.data.remote.requests

import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm

data class AccountRequest(
    val email: String,
    val password: String,
    val userName: String

)


/*
    1. check that registering a new user works
    2. check that login works
 */


data class NewSetRequest(
    val set: NetworkSet,
    val termList : List<NetworkTerm>
)











