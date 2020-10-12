package com.example.quizletclone.data.repo

import com.example.quizletclone.data.remote.requests.AddFolderRequest
import com.example.quizletclone.other.Resource

interface RepoInterface {

    suspend fun login(email: String, password: String, userName: String) : Resource<String>

    suspend fun register(email: String, password : String, userName: String) : Resource<String>

    suspend fun addFolder(addFolderRequest: AddFolderRequest) : Resource<String>
}