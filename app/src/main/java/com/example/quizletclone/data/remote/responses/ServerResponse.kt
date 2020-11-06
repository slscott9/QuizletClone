package com.example.quizletclone.data.remote.responses


//Json will be deserialized into these classes from the network

data class ServerResponse (
    val successful: String,
    val message : String
)

