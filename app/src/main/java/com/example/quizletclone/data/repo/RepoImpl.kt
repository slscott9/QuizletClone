package com.example.quizletclone.data.repo

import android.app.Application
import com.example.quizletclone.data.domain.DomainSet
import com.example.quizletclone.data.local.LocalDataSourceInterface
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.AddFolderRequest
import com.example.quizletclone.data.remote.requests.SearchRequest
import com.example.quizletclone.data.remote.requests.SetWithTermsRequest
import com.example.quizletclone.data.remote.responses.NetworkSet
import com.example.quizletclone.data.remote.responses.SearchResponse
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.data.remote.responses.asDomainModel
import com.example.quizletclone.data.remote.service.RemoteDataSourceInterface
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
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

    override suspend fun addFolder(addFolderRequest: AddFolderRequest): Resource<String> = withContext(Dispatchers.IO){
        try {
            val response = remoteDataSource.addFolder(addFolderRequest)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()?.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e: Exception){
            Resource.error("No internet connection", null)
        }
    }

    override suspend fun getSetsWithSearch(searchRequest: SearchRequest): Resource<List<DomainSet>> = withContext(Dispatchers.IO){

        Timber.i("In getSetsWithSearch repo method")

        try {
            val response = remoteDataSource.getSetsWithSearch(searchRequest)
            if(response.isSuccessful && response.body()!!.successful){
                Timber.i(response.body()!!.setList.toString())
                Resource.success(response.body()!!.asDomainModel())
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e : Exception){
            Resource.error("No internet connection", null)
        }
    }

    override suspend fun addNewSet(addSetRequest: SetWithTermsRequest): Resource<String> = withContext(Dispatchers.IO) {
        try {
            val response = remoteDataSource.addNewSet(addSetRequest)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()?.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e : Exception){
            Resource.error("No internet connection", null)
        }
    }
}