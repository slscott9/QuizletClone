package com.example.quizletclone.ui.sets

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.repo.RepoInterface
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.Flow

class SetDetailViewModel @ViewModelInject constructor(
    private val repo: RepoInterface
) : ViewModel(){

    private val _setId = MutableLiveData<Long>()
    val setId: LiveData<Long> = _setId

    private val setWithTerms = setId.switchMap {
        repo.getSetAndTermsWithId(it)
    }

    private val _set = setWithTerms.switchMap {
         liveData(Dispatchers.IO + viewModelScope.coroutineContext) {
            emit(it.set)
        }
    }
    val set: LiveData<Set> = _set

    private val _termList = setWithTerms.switchMap {
        liveData(Dispatchers.IO + viewModelScope.coroutineContext) {
            emit(it.termList)
        }
    }

    val termList: LiveData<List<Term>> = _termList

    fun setSetId(setId : Long) {
        _setId.value = setId
    }



}

