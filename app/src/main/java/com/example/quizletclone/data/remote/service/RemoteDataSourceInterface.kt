package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.dto.UserData
import com.example.quizletclone.data.dto.add.AddSetContainer
import com.example.quizletclone.data.entities.SetContainer
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.other.Resource
import okhttp3.ResponseBody

import retrofit2.Response

interface RemoteDataSourceInterface {

    suspend fun register( registerRequest: AccountRequest) : ServerResponse

    suspend fun login( loginRequest: AccountRequest): Response<ResponseBody>

    suspend fun addSetAndTerms(setContainer: List<AddSetContainer>, userEmail: String) : ServerResponse

    suspend fun getUserData(userEmail: String) : UserData







}