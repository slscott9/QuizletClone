package com.example.quizletclone.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizletDao {

    @Insert
    suspend fun insertSet(set: Set) : Long

    @Insert
    suspend fun insertTerms(termList : List<Term>)

    @Insert
    suspend fun insertFolder(folder: Folder)

    @Transaction
    @Query("select * from set_table where setid = :setId")
     fun getSetsAndTermsWithId(setId: Long) : LiveData<SetWithTerms>

    @Query("select * from term_table where setId = :setId")
    suspend fun getAllTermsWithSetId(setId: Long) : List<Term>

    @Query("select  * from set_table where setId =:setId")
    suspend fun getSetWithId(setId: Long) : Set


    //One shot flows

    @Query("select * from set_table")
    fun getAllSets() : Flow<List<Set>>

    @Query("select * from folder_table")
    fun getAllFolders() : Flow<List<Folder>>


    //Search sets
    @Query("select * from set_table where setName = :setName")
    suspend fun getSetsWithSearchParam(setName: String) : List<Set>


}

