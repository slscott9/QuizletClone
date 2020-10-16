package com.example.quizletclone.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term

@Dao
interface QuizletDao {

    @Insert
    suspend fun insertSet(set: Set) : Long

    @Insert
    suspend fun insertTerms(termList : List<Term>)

    @Transaction
    @Query("select * from set_table where setid = :setId")
     fun getSetsAndTermsWithId(setId: Long) : LiveData<SetWithTerms>


}