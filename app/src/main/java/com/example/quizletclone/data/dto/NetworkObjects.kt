package com.example.quizletclone.data.dto

import androidx.lifecycle.Transformations.map
import com.example.quizletclone.data.entities.*
import com.example.quizletclone.data.entities.FolderContainer
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetContainer
import com.example.quizletclone.data.entities.TermContainer



data class UserData(
    val userSets: List<NetworkSet>,
    val folders: List<NetworkSet>?

)

data class NetworkSet(
    val id: Long,
    val setName: String,
    val userEmail: String,
    val termCount:Int,
    val isSynced :Boolean,
    val terms :List<NetworkTerm>
)

data class NetworkTerm(
    val id: Long,
    val parentSetId: Long,
    val question : String,
    val answer: String,
    val userEmail: String,
    val isSynced: Boolean
)



fun List<NetworkTerm>.asDatabaseModels(parentSetId: Long) : List<Term> {
    return map {
        Term(
            termId = it.id,
            setId = parentSetId,
            question = it.question,
            answer = it.answer,
            userEmail = it.userEmail,
            isSynced = it.isSynced
        )
    }
}

fun NetworkSet.asDatabaseModel() : Set {
    return Set(
        setId = id,
        folderId = null,
        setName = setName,
        userEmail = userEmail,
        termCount = termCount,
        isSynced = isSynced
    )
}

fun List<NetworkSet>.asSetWithTerms() : List<SetWithTerms>{
    return map {
        SetWithTerms(
            set = it.asDatabaseModel(),
            termList = it.terms.asDatabaseModels(it.id)
        )
    }
}