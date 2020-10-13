package com.example.quizletclone.data.remote.service

import android.accounts.Account
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.AddFolderRequest
import com.example.quizletclone.data.remote.requests.SearchRequest
import com.example.quizletclone.data.remote.responses.SearchResponse
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteDataSourceInterface {

    suspend fun register( registerRequest: AccountRequest) : Response<ServerResponse>

    suspend fun login( loginRequest: AccountRequest): Response<ServerResponse>

    suspend fun addFolder(addFolderRequest: AddFolderRequest) : Response<ServerResponse>

    suspend fun getSetsWithSearch(searchRequest: SearchRequest) : Response<SearchResponse>


}