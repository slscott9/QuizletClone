package com.example.quizletclone.data.local

import com.example.quizletclone.data.entities.Folder

interface LocalDataSourceInterface {

    suspend fun getAllFolders() : List<Folder>
}