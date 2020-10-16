package com.example.quizletclone.data.local

import androidx.lifecycle.LiveData
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term

interface LocalDataSourceInterface {

    suspend fun insertSet(newSet: Set) : Long

    suspend fun insertTerms(termList : List<Term>)

     fun getSetAndTermsWithId(setId: Long) : LiveData<SetWithTerms>
}