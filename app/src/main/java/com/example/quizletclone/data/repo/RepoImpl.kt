package com.example.quizletclone.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.local.LocalDataSourceInterface
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.data.remote.service.RemoteDataSourceInterface
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
            Resource.success(response.message)
        }catch (e: Exception){
            Resource.error("Couldn't connect to server. Check internet connection", e.message)
        }

    }

    override suspend fun register(email: String, password: String, userName: String): Resource<String> = withContext(Dispatchers.IO) {
        try {
            val response = remoteDataSource.register(AccountRequest(email, password, userName))
            Resource.success(response.message)
        }catch (e: Exception){
            Resource.error("Couldn't connect to server. Check internet connection", e.message)
        }
    }



    //Inser set and terms into local database

    override suspend fun insertSet(newSet: Set): Long {
        return localDataSource.insertSet(newSet)
    }

    override suspend fun insertTerms(termList: List<Term>) {
        localDataSource.insertTerms(termList)
    }

    override suspend fun insertFolder(folder: Folder) {
        localDataSource.insertFolder(folder)

    }




    //Send new set to network

    override suspend fun sendSet(newSetRequest: NewSetRequest) : Resource<String> = withContext(Dispatchers.IO) {

        try {
            val response = remoteDataSource.sendSet(newSetRequest)
            Resource.success(response.message)
        }catch (e : Exception){
            Resource.error("Check network connection", e.message)
        }
    }



    override  fun getSetAndTermsWithId(setId: Long): LiveData<SetWithTerms> {
        return localDataSource.getSetAndTermsWithId(setId)
    }

    override suspend fun getAllTermsWithSetId(setId: Long): List<Term> {
        return localDataSource.getAllTermsWithSetId(setId)
    }

    override suspend fun getSetWithId(setId: Long): Set {

        return localDataSource.getSetWithId(setId)
    }

    override fun getAllSets(): Flow<List<Set>> {
        return localDataSource.getAllSets()
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return localDataSource.getAllFolders()

    }


    //get sets with search param

    override suspend fun getSetsWithSearchParam(setName: String): List<Set> {
        return localDataSource.getSetsWithSearchParam(setName)
    }
}