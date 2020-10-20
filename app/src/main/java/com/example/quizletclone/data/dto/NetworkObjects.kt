package com.example.quizletclone.data.dto

import com.example.quizletclone.data.entities.*
import com.example.quizletclone.data.entities.FolderContainer
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.SetContainer
import com.example.quizletclone.data.entities.TermContainer

data class NetworkSet(
    val setId: Long,
    val folderId: Long?,
    val userEmail: String,
    val setName: String,
    val termCount: Int = 0,
    val timeStamp: Long
)

data class NetworkFolder(
    val name: String,
    val folderId: Long,
    val userEmail: String,
    val userName: String,
    val description: String?,
    val timeStamp: Long

)

data class NetworkTerm(
    val termId: Long,
    val setId: Long,
    val term: String,
    val answer: String,
    val timeStamp: Long

)

data class NetworkTermContainer(
    val termList: List<NetworkTerm>
)

data class NetworkSetContainer(
    val setList : List<NetworkSet>
)

data class NetworkFolderContainer(
    val folderList : List<NetworkFolder>
)


fun NetworkFolderContainer.asDatabaseModels() : List<Folder>{
    return folderList.map{
        Folder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description,
            timeStamp= it.timeStamp

        )
    }
}

fun NetworkSetContainer.asDatabaseModels() : List<Set>{
    return setList.map{
        Set(
            setId = it.setId,
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            timeStamp= it.timeStamp

        )
    }
}

fun NetworkTermContainer.asDatabaseModels() : List<Term> {
    return termList.map {
        Term(
            setId = it.setId,
            termId = it.termId,
            question = it.term,
            answer = it.answer,
            timeStamp= it.timeStamp

        )
    }
}