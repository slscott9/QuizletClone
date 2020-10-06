package com.example.quizletclone.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizletclone.data.repo.RepoInterface
import com.example.quizletclone.other.Resource
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val repo: RepoInterface
) : ViewModel(){

    private val _loginStatus = MutableLiveData<Resource<String>>()
    val loginStatus: LiveData<Resource<String>> = _loginStatus

    fun login(email: String, password : String ) {
        _loginStatus.postValue(Resource.loading(null))

        if(email.isEmpty() || password.isEmpty()){
            _loginStatus.postValue(Resource.error("Please enter all fields", null))
            return
        }
        viewModelScope.launch {
            val result = repo.login(email, password, "")
            _loginStatus.postValue(result)
        }
    }
}