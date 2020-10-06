package com.example.quizletclone.data.local

import com.example.quizletclone.data.entities.Folder
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: QuizletDao
) : LocalDataSourceInterface {


    override suspend fun getAllFolders(): List<Folder> {
        TODO("Not yet implemented")
    }


}