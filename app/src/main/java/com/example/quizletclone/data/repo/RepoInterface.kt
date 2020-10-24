package com.example.quizletclone.data.repo

import androidx.lifecycle.LiveData
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.flow.Flow

interface RepoInterface {

    suspend fun login(email: String, password: String, userName: String): Resource<String>

    suspend fun register(email: String, password: String, userName: String): Resource<String>

    //insert sets and terms into local database
    suspend fun insertSet(newSet: Set) : Long

    suspend fun insertTerms(termList: List<Term>)

    suspend fun sendSet(newSetRequest: NewSetRequest) : Resource<String>

    suspend fun insertFolder(folder: Folder)


    //get sets and terms
     fun getSetAndTermsWithId(setId: Long) : LiveData<SetWithTerms>

    suspend fun getAllTermsWithSetId(setId: Long) : List<Term>

    suspend fun getSetWithId(setId: Long) : Set


    //One shot flow get all sets and folders
    fun getAllSets() : Flow<List<Set>>

    fun getAllFolders() : Flow<List<Folder>>


    //Search sets with query string
    suspend fun getSetsWithSearchParam(setName: String ) : List<Set>






}

