package com.example.quizletclone.data.remote.responses

import android.icu.text.CaseMap
import com.example.quizletclone.data.domain.DomainFolder
import com.example.quizletclone.data.domain.DomainSet
import com.example.quizletclone.data.domain.DomainTerm
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.ui.create.AddSetFragment


//Json will be deserialized into these classes from the network

data class ServerResponse (
    val successful: Boolean,
    val message : String
)

data class FolderListResponse(
    val folderList: List<NetworkFolder>
)

data class SearchResponse(
    val successful: Boolean,
    val message: String,
    val setList: List<NetworkSet>
)

//Response from server for an add set request
data class AddSetResponse(
    val setId: Int?,
    val successful: Boolean,
    val message: String
)

data class SetWithTermsResponse(
    val set: NetworkSet?,
    val termList : List<NetworkTerm?>,
    val successful: Boolean,
    val message: String
)

fun SetWithTermsResponse.asDomainTerms() : List<DomainTerm>{
    return termList.map {
        DomainTerm(
            id = it!!.id,
            term = it.term,
            answer = it.answer,
            setId = it.setId
        )
    }
}

fun NetworkSet.asDomainModel() : DomainSet {
    return DomainSet(
        setId = setId,
        folderId = folderId,
        userEmail = userEmail,
        setName = setName
    )
}

data class NetworkTerm(
    val id: Int,
    val setId: Int,
    val term: String,
    val answer: String
)


data class SetListResponse(
    val setList: List<NetworkSet>
)

data class NetworkFolder(
    val name: String,
    val folderId: Int,
    val userEmail: String,
    val userName: String,
    val description: String?
)

data class NetworkSet(
    val setId: Int,
    val folderId: Int,
    val setName : String,
    val userEmail: String

)

fun FolderListResponse.asDomainModel() : List<DomainFolder> {
    return folderList.map {
        DomainFolder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description
        )
    }
}

fun SearchResponse.asDomainModel() : List<DomainSet> {
    return setList.map{
        DomainSet(
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            termCount = setList.size,
            setId = it.setId
        )
    }
}

fun SetListResponse.asDomainModel() : List<DomainSet> {
    return setList.map{
        DomainSet(
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,
            termCount = setList.size,
            setId = it.setId
        )
    }
}


fun FolderListResponse.asDatabaseModel() : List<Folder> {
    return folderList.map {
        Folder(
            name = it.name,
            folderId = it.folderId,
            userEmail = it.userEmail,
            userName = it.userName,
            description = it.description
        )
    }
}

fun SetListResponse.asDatabaseModel() : List<Set> {
    return setList.map{
        Set(
            folderId = it.folderId,
            userEmail = it.userEmail,
            setName = it.setName,

         )
    }
}

