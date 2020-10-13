package com.example.quizletclone.data.repo

import com.example.quizletclone.data.domain.DomainSet
import com.example.quizletclone.data.remote.requests.AddFolderRequest
import com.example.quizletclone.data.remote.requests.SearchRequest
import com.example.quizletclone.data.remote.requests.SetWithTermsRequest
import com.example.quizletclone.data.remote.responses.NetworkSet
import com.example.quizletclone.data.remote.responses.SearchResponse
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoInterface {

    suspend fun login(email: String, password: String, userName: String): Resource<String>

    suspend fun register(email: String, password: String, userName: String): Resource<String>

    suspend fun addFolder(addFolderRequest: AddFolderRequest): Resource<String>

    suspend fun getSetsWithSearch(searchRequest: SearchRequest): Resource<List<DomainSet>>

    suspend fun addNewSet(addSetRequest: SetWithTermsRequest): Resource<String>

}

