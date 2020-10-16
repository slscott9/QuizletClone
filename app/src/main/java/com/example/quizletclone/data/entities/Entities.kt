package com.example.quizletclone.data.entities

import androidx.room.*
import com.example.quizletclone.data.dto.*
import java.util.*

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey
    val userEmail: String,
    val displayName: String,
    val password: String
)



@Entity(tableName = "folder_table")
data class Folder(

    @PrimaryKey(autoGenerate = true)
    val folderId: Long = 0,
    val name: String,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val timeStamp: Long
)

data class FolderwithSets(
    @Embedded val folder: Folder,
    @Relation(
        parentColumn = "folderId",
        entityColumn = "folderId"
    )
    val setList: List<Set>
)

@Entity(tableName = "set_table", foreignKeys = arrayOf(ForeignKey(entity = Folder::class,
    parentColumns = arrayOf("folderId"),
    childColumns = arrayOf("folderId"),
    onDelete = ForeignKey.CASCADE
)))
data class Set(
    @PrimaryKey(autoGenerate = true)
    val setId: Long = 0,
    val folderId: Long?,
    val setName : String,
    val userEmail: String,
    val termCount: String = "",
    val timeStamp: Long

)

data class SetWithTerms(
    @Embedded val set: Set,
    @Relation(
        parentColumn = "setId",
        entityColumn = "setId"
    )
    val termList: List<Term>
)



@Entity(tableName = "term_table", foreignKeys = arrayOf(ForeignKey(entity = Set::class,
    parentColumns = arrayOf("setId"),
    childColumns = arrayOf("setId"),
    onDelete = ForeignKey.CASCADE

)))
data class Term(
    @PrimaryKey(autoGenerate = true)
    val termId: Long = 0,
    val setId: Long,
    val question: String,
    val answer: String,
    val timeStamp: Long

)




data class TermContainer(
    val termList: List<Term>
)

data class SetContainer(
    val setList : List<Set>
)

data class FolderContainer(
    val folderList : List<Folder>
)

fun FolderContainer.asDomainModel() : List<DomainFolder>{
    return folderList.map{
        DomainFolder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
             timeStamp= it.timeStamp

        )
    }
}

fun SetContainer.asDomainModel() : List<DomainSet>{
    return setList.map{
        DomainSet(
            setId = it.setId,
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            timeStamp= it.timeStamp

        )
    }
}

fun TermContainer.asDomainModel() : List<DomainTerm> {
    return termList.map {
        DomainTerm(
            setId = it.termId,
            termId = it.termId,
            term = it.question,
            answer = it.answer,
            timeStamp= it.timeStamp

        )
    }
}



fun FolderContainer.asNetworkModels() : List<NetworkFolder>{
    return folderList.map{
        NetworkFolder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
            timeStamp= it.timeStamp
        )
    }
}

fun SetContainer.asNetworkModels() : List<NetworkSet>{
    return setList.map{
        NetworkSet(
            setId = it.setId,
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            termCount = it.termCount.toInt(),
            timeStamp= it.timeStamp

        )
    }
}

fun TermContainer.asNetworkModels() : List<NetworkTerm> {
    return termList.map {
        NetworkTerm(
            setId = it.termId,
            termId = it.termId,
            term = it.question,
            answer = it.answer,
            timeStamp= it.timeStamp

        )
    }
}



