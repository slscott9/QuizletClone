package com.example.quizletclone.ui.home

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.entities.asDomainFolder
import com.example.quizletclone.data.entities.asDomainSet
import com.example.quizletclone.data.repo.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class HomeViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
    private val sharedPreferences: SharedPreferences
) : ViewModel(){

    val _allSets = repo.getAllSets()
        .map { it.asDomainSet() }
        .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

    val _allFolders = repo.getAllFolders()
        .map { it.asDomainFolder() }
        .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)





}