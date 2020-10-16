package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.remote.requests.*
import com.example.quizletclone.data.remote.responses.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.annotation.PostConstruct

interface QuizletApi {

    @POST("/register")
    suspend fun register(@Body registerRequest: AccountRequest) : Response<ServerResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: AccountRequest): Response<ServerResponse>


    @POST("/addSetWithTerms")
    suspend fun addNewSet(@Body newSetRequest: NewSetRequest): Response<ServerResponse>



}

