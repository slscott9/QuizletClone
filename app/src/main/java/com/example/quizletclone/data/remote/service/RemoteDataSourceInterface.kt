package com.example.quizletclone.data.remote.service

import android.accounts.Account
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import retrofit2.Response

interface RemoteDataSourceInterface {

    suspend fun register( registerRequest: AccountRequest) : Response<ServerResponse>

    suspend fun login( loginRequest: AccountRequest): Response<ServerResponse>

}