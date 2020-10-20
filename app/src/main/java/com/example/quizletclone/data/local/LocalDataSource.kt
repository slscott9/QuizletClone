package com.example.quizletclone.data.local

import androidx.lifecycle.LiveData
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetWithTerms
import com.example.quizletclone.data.entities.Term
import kotlinx.coroutines.flow.Flow
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

    override  fun getSetAndTermsWithId(setId: Long): LiveData<SetWithTerms> {
        return dao.getSetsAndTermsWithId(setId)
    }

    override suspend fun getAllTermsWithSetId(setId: Long): List<Term> {
        return dao.getAllTermsWithSetId(setId)
    }

    override suspend fun getSetWithId(setId: Long): Set {
        return dao.getSetWithId(setId)
    }

    override fun getAllSets(): Flow<List<Set>> {
        return dao.getAllSets()
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return dao.getAllFolders()
    }

    override suspend fun getSetsWithSearchParam(setName: String): List<Set> {
        return dao.getSetsWithSearchParam(setName)
    }
}