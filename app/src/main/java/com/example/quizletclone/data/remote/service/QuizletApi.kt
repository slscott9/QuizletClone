package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface QuizletApi {

    @POST("/register")
    suspend fun register(@Body registerRequest: AccountRequest) : Response<ServerResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: AccountRequest): Response<ServerResponse>
}