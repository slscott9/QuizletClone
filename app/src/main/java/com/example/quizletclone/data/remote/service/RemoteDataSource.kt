package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.AddFolderRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val quizletApi: QuizletApi
) : RemoteDataSourceInterface{


    override suspend fun register(registerRequest: AccountRequest): Response<ServerResponse> {
        return quizletApi.register(registerRequest)

    }

    override suspend fun login(loginRequest: AccountRequest): Response<ServerResponse> {
        return quizletApi.login(loginRequest)

    }

    override suspend fun addFolder(addFolderRequest: AddFolderRequest): Response<ServerResponse> {
        return quizletApi.addFolder(addFolderRequest)
    }


}