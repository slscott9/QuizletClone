package com.example.quizletclone.data.remote.service

import com.example.quizletclone.data.remote.requests.*
import com.example.quizletclone.data.remote.responses.AddSetResponse
import com.example.quizletclone.data.remote.responses.SearchResponse
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.data.remote.responses.SetWithTermsResponse
import com.example.quizletclone.other.Resource
import retrofit2.Response
import java.util.concurrent.Flow
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

    override suspend fun getSetsWithSearch(searchRequest: SearchRequest): Response<SearchResponse> {
        return quizletApi.searchSets(searchRequest)
    }

    override suspend fun addNewSet(addSetRequest: SetWithTermsRequest): Response<AddSetResponse> {
        return quizletApi.addNewSet(addSetRequest)
    }

    override suspend fun getSetTermsWithId(setId: Int): Response<SetWithTermsResponse> {
        return quizletApi.getSetTermsWithId(GetSetWithTermsRequest(setId))
    }
}