package com.example.quizletclone.data.repo

import androidx.lifecycle.LiveData
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.dto.add.AddSetContainer
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.local.LocalDataSource
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface RepoInterface {

    suspend fun login(userName: String, password: String): Resource<String>

    suspend fun register(email: String, userName: String, password: String): Resource<String>




    //insert sets and terms into local database
    suspend fun insertSet(newSet: Set) : Long

    suspend fun insertTerms(termList: List<Term>)

    suspend fun insertFolder(folder: Folder)



    //network calls



    //get sets and terms
     fun getSetAndTermsWithId(setId: Long) : LiveData<SetWithTerms>

    suspend fun getAllTermsWithSetId(setId: Long) : List<Term>

    suspend fun getSetWithId(setId: Long) : Set




    //One shot flow get all sets and folders
    fun getAllSets() : Flow<List<Set>>

    fun getAllFolders() : Flow<List<Folder>>


    //Search sets with query string
    suspend fun getSetsWithSearchParam(setName: String ) : List<Set>


    suspend fun getUnSyncedSets(isSynced: Boolean): List<Set>


    suspend fun getUnSyncedTermsWithSetId(isSynced: Boolean, setId: Long): List<Term>

    suspend fun sendSetsToNetwork(setContainer: List<AddSetContainer>, userEmail: String) : ServerResponse



}

