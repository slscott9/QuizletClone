package com.example.quizletclone.data.local

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceInterface {

    //Insert folder, set, terms

    suspend fun insertSet(newSet: Set) : Long

    suspend fun insertTerms(termList : List<Term>)

    suspend fun insertFolder(folder: Folder)

    //get sets, terms,

     fun getSetAndTermsWithId(setId: Long) : LiveData<SetWithTerms>

    suspend fun getAllTermsWithSetId(setId: Long) : List<Term>

    suspend fun getSetWithId(setId: Long) : Set




    //one shot flow for all folders and sets
    fun getAllSets() : Flow<List<Set>>

    fun getAllFolders() : Flow<List<Folder>>


    //search sets with query string
    suspend fun getSetsWithSearchParam(setName: String) : List<Set>


    suspend fun getUnSyncedSets(isSynced : Boolean) : List<Set>

    suspend fun getUnSyncedTermsWithSetId(isSynced: Boolean, setId: Long) : List<Term>


    suspend fun deleteAllFolders()

    suspend fun deleteAllSets()

    suspend fun insertSetWithTerms(setWithTerms: List<SetWithTerms>)

}