package com.example.quizletclone.ui.folders

import android.content.SharedPreferences
import android.provider.Settings
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FolderFragmentViewModel @ViewModelInject constructor(
    private val repo: RepoInterface,
     private val sharedPreferences: SharedPreferences
) : ViewModel(){



    private val _folderSentStatus = MutableLiveData<Resource<String>>()
    val folderSentStatus: LiveData<Resource<String>> = _folderSentStatus

//    fun sendNewFolderToNetwork(folderName: String, description: String?) {
//
//        GlobalScope.launch {
//            repo.addFolder(AddFolderRequest(
//                folderName,
//                sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL)?: Constants.NO_EMAIL,
//                description
//                ))
//        }
//
//    }
}