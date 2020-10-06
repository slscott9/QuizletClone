package com.example.quizletclone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey
    val userEmail: String,
    val displayName: String,
    val password: String
)

@Entity(tableName = "folder_table")
data class Folder(

    @PrimaryKey
    val id: Int,
    val userEmail: String,
    val name: String
)

@Entity(tableName = "set_table")
data class Set(
    @PrimaryKey
    val id: Int,
    val userEmail: String,
    val term: String,
    val answer: String
)