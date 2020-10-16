package com.example.quizletclone.data.repo

import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.other.Resource

interface RepoInterface {

    suspend fun login(email: String, password: String, userName: String): Resource<String>

    suspend fun register(email: String, password: String, userName: String): Resource<String>

    //insert sets and terms into local database
    suspend fun insertSet(newSet: Set) : Long

    suspend fun insertTerms(termList: List<Term>)

    suspend fun sendSet(newSet: NetworkSet, termList : List<NetworkTerm>) : Resource<String>



}

