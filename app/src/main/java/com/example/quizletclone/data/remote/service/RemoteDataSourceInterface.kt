package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import retrofit2.Response
import retrofit2.http.Body

interface RemoteDataSourceInterface {

    suspend fun register( registerRequest: AccountRequest) : Response<ServerResponse>

    suspend fun login( loginRequest: AccountRequest): Response<ServerResponse>

}