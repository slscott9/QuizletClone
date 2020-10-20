package com.example.quizletclone.ui.create

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.entities.*
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.remote.requests.NewSetRequest
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


    private val _addSetStatus = MutableLiveData<Resource<String>>()
    val addSetStatus = _addSetStatus

    private var _setId: Long = 0L
    val setId: Long
    get() = _setId


    fun insertSet(setName: String, termList: List<AddSetFragment.Term>) {

        _addSetStatus.postValue(Resource.loading(null))

        val set = createSetToAdd(setName, null, termList) //create set to insert into db

        viewModelScope.launch(Dispatchers.IO) {
            _setId =
                repo.insertSet(set) //suspend function does not block, then resumes where it was called when finished
            Timber.i("Set id after insert is $_setId")

            val databaseTerms = ArrayList<Term>()

            termList.forEach {
                databaseTerms.add(
                    Term(
                        setId = _setId,
                        question = it.term,
                        answer = it.answer,
                        timeStamp = Date.from(Instant.now()).time,

                    )
                )
            }

            repo.insertTerms(databaseTerms) //suspend function does not block main thread then continues where it was called when done

            val databaseTermList = repo.getAllTermsWithSetId(_setId)
            val databaseSet = repo.getSetWithId(_setId)

            val response = repo.sendSet(
                    NewSetRequest( databaseSet.asNetworkSet(), databaseTermList.asNetworkTerms())
               )

            _addSetStatus.postValue(response)

        }
    }





    private fun createSetToAdd(setName: String, folderId: Int?, termList: List<AddSetFragment.Term>) : Set {
        return Set(
            setName = setName,
            userEmail = sharedPreferences.getString(
                Constants.KEY_LOGGED_IN_EMAIL,
                Constants.NO_EMAIL
            ).toString(),
            folderId = null,
            termCount = termList.size.toString(),
            timeStamp = Date.from(Instant.now()).time
        )
    }

    data class SetInsertSuccess(
        val setId: Long,
        val response: Resource<String>
    )

    /*

     */
}