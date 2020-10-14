package com.example.quizletclone.ui.sets

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.repo.RepoInterface

class SetDetailViewModel @ViewModelInject constructor(
    private val repo: RepoInterface
) : ViewModel(){


    private val setId = MutableLiveData<Int>()

    private val set : LiveData<Set>


}

