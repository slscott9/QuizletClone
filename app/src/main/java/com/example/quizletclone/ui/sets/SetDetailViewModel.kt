package com.example.quizletclone.ui.sets

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.Flow

class SetDetailViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
    private val sharedPreferences: SharedPreferences
) : ViewModel(){

    private val _setId = MutableLiveData<Long>()
    val setId: LiveData<Long> = _setId

    val userName = sharedPreferences.getString(Constants.USER_NAME, Constants.NO_USERNAME) ?: ""

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

