package com.example.quizletclone.test

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.quizletclone.data.repo.RepoInterface

class TestFragmentAddViewModel @ViewModelInject constructor(
    private val repository: RepoInterface
) : ViewModel(){




}