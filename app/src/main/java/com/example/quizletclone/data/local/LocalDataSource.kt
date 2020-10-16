package com.example.quizletclone.data.local

import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: QuizletDao
) : LocalDataSourceInterface {

    override suspend fun insertSet(newSet: Set): Long {
        return dao.insertSet(newSet)
    }

    override suspend fun insertTerms(termList: List<Term>) {
        dao.insertTerms(termList)
    }
}