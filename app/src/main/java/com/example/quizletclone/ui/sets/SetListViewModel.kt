package com.example.quizletclone.ui.sets

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.entities.asDomainSet
import com.example.quizletclone.data.repo.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class SetListViewModel @ViewModelInject constructor(
    private val repo: RepoInterface

): ViewModel() {


    val allSets = repo.getAllSets()
        .map { it.asDomainSet() }
        .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)


}