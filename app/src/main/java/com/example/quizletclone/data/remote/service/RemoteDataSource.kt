package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.dto.add.AddSetContainer
import com.example.quizletclone.data.entities.SetContainer
import com.example.quizletclone.data.remote.SafeApiRequest
import com.example.quizletclone.data.remote.requests.*
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.util.concurrent.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val quizletApi: QuizletApi
) : RemoteDataSourceInterface , SafeApiRequest() {




    override suspend fun register(registerRequest: AccountRequest)  = apiRequest {quizletApi.register(registerRequest)}


    override suspend fun login(loginRequest: AccountRequest) = quizletApi.login(loginRequest)

    override suspend fun addSetAndTerms(
        setContainer: List<AddSetContainer>,
        userEmail: String
    ): ServerResponse {
        return quizletApi.addSetTerms(setContainer, userEmail)

    }
}