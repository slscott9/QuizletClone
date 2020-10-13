package com.example.quizletclone.data.remote.requests

import com.example.quizletclone.ui.create.AddSetFragment

data class AccountRequest(
    val email: String,
    val password: String,
    val userName: String
)


data class AddFolderRequest(
    val folderName: String,
    val userEmail: String,
    val description: String?

)

data class SearchRequest(
    val userEmail: String,
    val searchParam: String
)

data class SetWithTermsRequest(
    val set: SetRequest,
    val termList : List<AddSetFragment.Term>

)

data class SetRequest(
    val userEmail: String,
    val folderId: Int?,
    val setName: String

)



