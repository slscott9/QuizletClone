package com.example.quizletclone.data.entities

import android.icu.text.CaseMap
import androidx.room.*
import com.example.quizletclone.data.dto.*
import com.example.quizletclone.data.dto.add.AddTerm
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
    val isSynced: Boolean
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
    val termCount: Int,
    val isSynced: Boolean

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
    val isSynced: Boolean,
    val userEmail: String

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

fun List<Set>.asDomainSet() : List<DomainSet> {
    return map {
        DomainSet(
            setId = it.setId,
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            isSynced = it.isSynced,
            termCount = it.termCount.toString()
        )
    }
}

fun List<Folder>.asDomainFolder() : List<DomainFolder> {
    return map {
        DomainFolder(

            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
            isSynced = it.isSynced
        )
    }
}

fun FolderContainer.asDomainModel() : List<DomainFolder>{
    return folderList.map{
        DomainFolder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
            isSynced = it.isSynced

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
            isSynced = it.isSynced,
            termCount = it.termCount.toString()

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
            isSynced = it.isSynced

        )
    }
}



fun FolderContainer.asNetworkModels() : List<NetworkFolder>{
    return folderList.map{
        NetworkFolder(
            folderName = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
            isSynced = it.isSynced
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
            isSynced = it.isSynced

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
            isSynced = it.isSynced,
            userEmail = it.userEmail

        )
    }
}

fun List<Term>.asNetworkTerms() : List<NetworkTerm> {
    return map {
        NetworkTerm(
            termId = it.termId,
            setId = it.setId,
            term = it.question,
            answer = it.answer,
            isSynced = it.isSynced,
            userEmail = it.userEmail
        )
    }
}

fun Set.asNetworkSet() : NetworkSet {
    return NetworkSet(
        setId = setId,
        folderId = folderId,
        userEmail = userEmail,
        setName = setName,
        termCount = termCount.toInt(),
        isSynced = isSynced
    )
}




