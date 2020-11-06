package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.dto.add.AddSetContainer
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.remote.requests.*
import com.example.quizletclone.data.remote.responses.*
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.annotation.PostConstruct

interface QuizletApi {

    @POST("/register")
    suspend fun register(@Body registerRequest: AccountRequest) : Response<ServerResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: AccountRequest): Response<ResponseBody> //server sends a response with a body and http status code. this return value must match


    @POST("/addSetWithTerms")
    suspend fun addNewSet(@Body newSetRequest: NewSetRequest): Response<ServerResponse>


    @POST("/addSet/terms/{userEmail}")
    suspend fun addSetTerms(@Body setContainer:  List<AddSetContainer>, @Path("userEmail") userEmail: String) : ServerResponse





}

