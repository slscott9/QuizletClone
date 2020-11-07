package com.example.quizletclone.data.dto.add

import androidx.lifecycle.Transformations.map
import com.example.quizletclone.data.entities.*
import com.example.quizletclone.data.entities.Set

data class AddFolderContainer(
    val folderName: String,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val isSynced: Boolean,

    val userSets: List<AddSetContainer>?
)

data class AddSetContainer(
    val setName: String,
    val userEmail: String,
    val termCount: Int,
    val isSynced: Boolean,

    val terms: List<AddTerm>
)

data class AddSet(
    val setName: String,
    val userEmail: String,
    val termCount: Int,
    val isSynced: Boolean,
)

data class AddTerm(
    val question: String,
    val answer: String,
    val userEmail: String
)


fun List<FolderwithSets>.asAddFolderContainer(addSetContainerList: List<AddSetContainer>) : List<AddFolderContainer> {
    return map {
        AddFolderContainer(
            folderName = it.folder.name,
            userEmail = it.folder.userEmail,
            userName = it.folder.userName,
            description = it.folder.description,
            isSynced = it.folder.isSynced,
            userSets = addSetContainerList
        )
    }
}







fun List<Set>.asAddSets() : List<AddSet>{
    return map {
        AddSet(
            setName = it.setName,
            userEmail = it.userEmail,
            termCount = it.termCount,
            isSynced = it.isSynced
        )
    }
}

fun List<SetWithTerms>.asSetContainer() : List<AddSetContainer>{
    return map {
        AddSetContainer(
            setName = it.set.setName,
            userEmail = it.set.userEmail,
            termCount = it.set.termCount,
            isSynced = it.set.isSynced,
            terms = it.termList.asTermContainer()

        )
    }
}

fun List<Term>.asTermContainer() : List<AddTerm> {
    return map {
        AddTerm(
            question = it.question,
            answer = it.answer,
            userEmail = it.userEmail
        )
    }
}



