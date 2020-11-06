package com.example.quizletclone.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.dto.add.AddSetContainer
import com.example.quizletclone.data.entities.*
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.local.LocalDataSourceInterface
import com.example.quizletclone.data.remote.requests.AccountRequest
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.data.remote.service.RemoteDataSourceInterface
import com.example.quizletclone.data.remote.service.ResponseHandler
import com.example.quizletclone.other.Resource
import com.example.quizletclone.ui.create.AddSetViewModel
import com.example.quizletclone.utils.ApiException
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val responseHandler: ResponseHandler,
    private val localDataSource: LocalDataSourceInterface,
    private val remoteDataSource: RemoteDataSourceInterface,
    private val context: Application
) : RepoInterface {


    /*
        Since repo is single source of truth for the application, I dont think the view model should be have a monster function
        that inserts and then sends data to the server. This is the repository's job since it has access to remote and local
        data sources. Repo handles the errors and sends the view model a message detailing what the error was.

        It is not the view models job to do this. Repo sends view model data and view model prepares this data to be displayed
     */


    //Network calls
    override suspend fun login(userName: String, password: String): Resource<String> {

        return try{

            val response = remoteDataSource.login(AccountRequest(userName = userName, password = password, email = ""))

            when {
                response.isSuccessful -> Resource.success("Success")
                response.code() == 401 -> Resource.error("Invalid username or password", null)
                else -> Resource.error("unknown error", null)

            }

        }catch (e : Exception){
            Resource.error("Check network connection", null)
        }
    }

    override suspend fun register(
        email: String,
        userName: String,
        password: String
    ): Resource<String> {

        return try {

            val response = remoteDataSource.register(AccountRequest(userName = userName, password = password, email = email))
            Resource.success(response.message)

        }catch (e : Exception){
            Resource.error(e.message ?: "Check internet connection", null)
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


    data class SetInsertSuccess(
        val setId: Long,
        val response: Resource<String>
    )




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



    //get unsynced sets and get terms with set id

    override suspend fun getUnSyncedSets(isSynced: Boolean): List<Set> {
        return localDataSource.getUnSyncedSets(isSynced)
    }

    override suspend fun getUnSyncedTermsWithSetId(isSynced: Boolean, setId: Long): List<Term> {
        return localDataSource.getUnSyncedTermsWithSetId(isSynced, setId)
    }

    override suspend fun sendSetsToNetwork(
        setContainer: List<AddSetContainer>,
        userEmail: String
    ): ServerResponse {
        return remoteDataSource.addSetAndTerms(setContainer, userEmail)
    }
}