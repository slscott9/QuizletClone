package com.example.quizletclone.ui.folders

import android.content.SharedPreferences
import android.provider.Settings
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.entities.Folder
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

class FolderFragmentViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
     private val sharedPreferences: SharedPreferences
) : ViewModel(){



    /*
        1. extract folder name and description which is optional
        2. insert into database
     */

    private val userEmail : String = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL).toString()
    private val userName : String = sharedPreferences.getString(Constants.USER_NAME, Constants.NO_USERNAME).toString()

    fun insertNewFolder(folderName : String, folderDescription: String?){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertFolder(
                Folder(
                    name = folderName,
                    userEmail = userEmail,
                    userName = userName,
                    description = folderDescription,
                    isSynced = false
                )
            )
        }
    }
}