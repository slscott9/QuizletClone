package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.data.remote.responses.ServerResponse

import retrofit2.Response

interface RemoteDataSourceInterface {

    suspend fun register( registerRequest: AccountRequest) :ServerResponse

    suspend fun login( loginRequest: AccountRequest): ServerResponse



    suspend fun sendSet(newSetRequest: NewSetRequest):ServerResponse




}