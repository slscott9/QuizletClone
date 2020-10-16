package com.example.quizletclone.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term

@Dao
interface QuizletDao {

    @Insert
    suspend fun insertSet(set: Set) : Long

    @Insert
    suspend fun insertTerms(termList : List<Term>)


}