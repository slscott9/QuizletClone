package com.example.quizletclone.ui.search

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import kotlin.coroutines.coroutineContext

class SearchViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
    private val sharedPreferences: SharedPreferences

) : ViewModel() {


    private val searchChannel = ConflatedBroadcastChannel<String>()
    private val _errorMessage = MutableLiveData<String>()
    val errMessage = _errorMessage

    private val _userName: String? = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL).toString()
    val userName = _userName


    //offer sends element to all subscribers
    fun setSearchQuery(search: String) {
        Timber.i(search)
        searchChannel.offer(search)
    }

//    private val _searchList = searchChannel.asFlow()
//        .map { search ->
//
//            repo.getSetsWithSearch(
//                SearchRequest(
//                    searchParam = search,
//                    userEmail = sharedPreferences.getString(
//                        Constants.KEY_LOGGED_IN_EMAIL,
//                        Constants.NO_EMAIL
//                    ).toString()
//                ))
//        }
//
//    val searchList = _searchList.asLiveData()









}