package com.example.quizletclone.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.quizletclone.data.repo.RepoInterface

class HomeViewModel @ViewModelInject constructor(
    private val repoInterface: RepoInterface
) : ViewModel(){



}