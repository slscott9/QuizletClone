package com.example.quizletclone.data.local

import android.icu.text.CaseMap
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.User

@Database(entities = [User::class, Folder::class, Set::class], version = 5)
abstract class QuizletDatabase : RoomDatabase() {

    abstract fun dao() : QuizletDao
}