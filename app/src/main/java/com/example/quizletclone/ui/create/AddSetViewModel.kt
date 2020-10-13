package com.example.quizletclone.ui.create

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizletclone.data.remote.requests.SetRequest
import com.example.quizletclone.data.remote.requests.SetWithTermsRequest
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class AddSetViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
    private val sharedPreferences: SharedPreferences
) : ViewModel(){


    private val _addSetStatus = MutableLiveData<Resource<String>>()
    val addSetStatus = _addSetStatus

    fun sendNewSetToNetwork(setName: String, termList: List<AddSetFragment.Term>){

        _addSetStatus.postValue(Resource.loading(null))

        Timber.i(sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL).toString())

        val setRequest = SetRequest(
            userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL).toString(),
            folderId = null,
            setName =  setName
        )
        val setWithTermRequest = SetWithTermsRequest(
            set = setRequest,
            termList = termList
        )
        GlobalScope.launch {

            val result = repo.addNewSet(setWithTermRequest)
            _addSetStatus.postValue(result)
        }
    }
}