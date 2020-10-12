package com.example.quizletclone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizletclone.data.remote.responses.NetworkFolder
import com.example.quizletclone.data.remote.responses.NetworkSet

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
    val folderId: Int,
    val userEmail: String,
    val name: String,
    val description: String?,
    val userName: String
)

@Entity(tableName = "set_table")
data class Set(


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val folderId: Int,
    val setName : String,
    val term: String,
    val answer : String,
    val userEmail: String
)


data class FolderContainer(
    val folderList: List<Folder>
)

data class SetContainer(
    val setList : List<Set>
)

fun FolderContainer.toNetworkModel() : List<NetworkFolder> {
    return folderList.map {
        NetworkFolder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            description = it.description,
            userName = it.userName
        )
    }
}

fun SetContainer.asNetworkModels() : List<NetworkSet> {
    return setList.map{
        NetworkSet(
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            term = it.term,
            answer = it.answer
        )

    }
}