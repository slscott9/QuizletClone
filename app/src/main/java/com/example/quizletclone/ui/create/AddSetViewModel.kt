package com.example.quizletclone.ui.create

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.dto.FragmentTerm
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.dto.asDatabaseModel
import com.example.quizletclone.data.entities.*
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.remote.requests.NewSetRequest
import com.example.quizletclone.data.repo.RepoImpl
import com.example.quizletclone.data.repo.RepoImpl.SetInsertSuccess
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.sql.Timestamp
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

class AddSetViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
    private val sharedPreferences: SharedPreferences
) : ViewModel(){



    val userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL)


    private var _setId = MutableLiveData<Long>()
    val setId: LiveData<Long> = _setId


    fun insertSet(setName: String, termList: List<FragmentTerm>) {
        val newSet = Set(
            setName = setName,
            folderId = null,
            userEmail = userEmail ?: "",
            termCount = termList.size,
            isSynced = false
        )

        viewModelScope.launch {
            val setId = repo.insertSet(newSet)

            val insertTermList = termList.asDatabaseModel(setId, userEmail ?: "")

            repo.insertTerms(insertTermList)

            _setId.value = setId

        }



    }





    /*

     */
}