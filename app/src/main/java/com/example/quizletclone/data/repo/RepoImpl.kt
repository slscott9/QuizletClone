package com.example.quizletclone.data.repo

import android.app.Application
import com.example.quizletclone.data.local.LocalDataSourceInterface
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.service.RemoteDataSourceInterface
import com.example.quizletclone.other.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val localDataSource: LocalDataSourceInterface,
    private val remoteDataSource: RemoteDataSourceInterface,
    private val context: Application
) : RepoInterface {

    override suspend fun login(email: String, password: String, userName: String): Resource<String> = withContext(Dispatchers.IO) {

        try {
            val response = remoteDataSource.login(AccountRequest(email, password, userName))
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()?.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e: Exception){
            Resource.error("Couldn't connect to server. Check internet connection", null)
        }

    }

    override suspend fun register(email: String, password: String, userName: String): Resource<String> = withContext(Dispatchers.IO) {
        try {
            val response = remoteDataSource.register(AccountRequest(email, password, userName))
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()?.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e: Exception){
            Resource.error("Couldn't connect to server. Check internet connection", null)
        }
    }
}