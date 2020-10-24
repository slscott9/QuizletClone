package com.example.quizletclone.data.local

import android.icu.text.CaseMap
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.entities.User

@Database(entities = [User::class, Folder::class, Set::class, Term::class], version = 14)
abstract class QuizletDatabase : RoomDatabase() {

    abstract fun dao() : QuizletDao
}

