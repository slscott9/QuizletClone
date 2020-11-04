package com.example.quizletclone.ui.create

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.dto.FragmentTerm
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
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


    private val _addSetStatus = MutableLiveData<Resource<SetInsertSuccess>>()
    val addSetStatus = _addSetStatus


    private var _setId: Long = 0L
    val setId: Long
    get() = _setId


//    fun insertSet(setName: String, termList: List<FragmentTerm>) {
//
//        _addSetStatus.postValue(Resource.loading(null))
//
//        val setToAdd = createSetToAdd(setName, null, termList)
//
//        //repo.addset is main safe running on Dispatcher.IO no need to specify view model dispatcher
//        viewModelScope.launch {
//            repo.addSet(setToAdd, terml)
//
//        }
//
//
//
//    }





     fun createSetToAdd(setName: String, folderId: Int?, termList: List<FragmentTerm>) : Set {
        return Set(
            setName = setName,
            userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL)!!,
            folderId = null,
            termCount = termList.size.toString(),
            timeStamp = Date.from(Instant.now()).time
        )
    }





    /*

     */
}