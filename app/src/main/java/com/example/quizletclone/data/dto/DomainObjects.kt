package com.example.quizletclone.data.dto

import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import java.util.*

data class DomainFolder(
    val name: String,
    val folderId: Long,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val isSynced: Boolean
)

data class DomainSet(
    val setId: Long,
    val folderId: Long?,
    val userEmail: String,
    val setName: String,
    val termCount: String,
    val isSynced: Boolean

)


data class DomainTerm(
    val termId: Long,
    val setId: Long,
    val term: String,
    val answer: String,

)

data class FragmentTerm(
    val question : String,
    val answer: String
)

fun Set.asDomainModel() : DomainSet {
    return DomainSet(
        setId = setId,
        folderId = folderId,
        userEmail = userEmail,
        setName = setName,
        termCount = termCount.toString(),
        isSynced = isSynced
    )
}

fun List<FragmentTerm>.asDatabaseModel(setId: Long, userEmail: String) : List<Term> {
    return map {
        Term(
            setId = setId,
            question = it.question,
            answer = it.answer,
            userEmail = userEmail,
            isSynced = true
        )
    }
}
