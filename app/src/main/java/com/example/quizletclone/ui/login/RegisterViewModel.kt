package com.example.quizletclone.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.remote.responses.ServerResponse
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class RegisterViewModel @ViewModelInject constructor(
    private val repo: RepoInterface
) : ViewModel(){

    private val _registerStatus = MutableLiveData<Resource<String>>()
    val registerStatus = _registerStatus


    fun register(email: String, password: String, userName: String) {
        _registerStatus.postValue(Resource.loading(null)) //emit loading status when main thread is available

        if(email.isEmpty() || password.isEmpty() || userName.isEmpty()){
            _registerStatus.postValue(Resource.error("Please fill out all fields", null))
            return
        }


        /*
            validation is good? then post value of what the server returns
         */
        viewModelScope.launch {
            val result = repo.register(email = email, password = password, userName = userName)
            _registerStatus.postValue(result)
        }
    }
}