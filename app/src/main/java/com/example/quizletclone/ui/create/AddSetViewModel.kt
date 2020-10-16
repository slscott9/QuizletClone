package com.example.quizletclone.ui.create

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizletclone.data.dto.NetworkSet
import com.example.quizletclone.data.dto.NetworkTerm
import com.example.quizletclone.data.entities.Set
import com.example.quizletclone.data.entities.Term
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Resource
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


    fun insertSet(setName: String, termList: List<AddSetFragment.Term>){

        _addSetStatus.postValue(Resource.loading(null))

        val set = Set(
            setName = setName,
            userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL).toString(),
            folderId = null,
            termCount = termList.size,
            timeStamp = Date.from(Instant.now()).time

        )

        GlobalScope.launch {
            val setId = repo.insertSet(set)
            val databaseTerms = ArrayList<Term>()

            termList.forEach {
                databaseTerms.add(
                    Term(
                        setId = setId,
                        question = it.term,
                        answer = it.answer,
                        timeStamp = Date.from(Instant.now()).time
                    )
                )
            }

            repo.insertTerms(databaseTerms)

            _addSetStatus.postValue(Resource.success(
                SetInsertSuccess(
                    setId = setId,
                    "Successfully added set"
                )
            ))
        }
    }

    data class SetInsertSuccess(
        val setId: Long,
        val message: String
    )

    /*

     */
}