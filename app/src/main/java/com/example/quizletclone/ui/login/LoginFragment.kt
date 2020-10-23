package com.example.quizletclone.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.BaseFragment
import com.example.quizletclone.R
import com.example.quizletclone.data.remote.BasicAuthInterceptor
import com.example.quizletclone.databinding.FragmentLoginBinding
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Constants.KEY_LOGGED_IN_EMAIL
import com.example.quizletclone.other.Constants.KEY_PASSWORD
import com.example.quizletclone.other.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var basicAuthInterceptor: BasicAuthInterceptor

    private var currentEmail: String ? = null
    private var currentPassword: String ? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isLoggedIn()){
            authenticateAPI(currentEmail ?: "", currentPassword ?: "")
            redirectLogin()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            currentEmail = email
            currentPassword = password
            viewModel.login(email, password)
        }

        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status){
                    Status.SUCCESS -> {
                        binding.loginProgressBar.visibility = View.GONE
                        showSnackBar(it.data ?: "Successfully logged in")

                        sharedPref.edit().putString(KEY_LOGGED_IN_EMAIL, currentEmail).apply()
                        sharedPref.edit().putString(KEY_PASSWORD, currentPassword).apply()

                        authenticateAPI(currentEmail?: "" , currentPassword ?: "")
                        redirectLogin()
                    }
                    Status.ERROR -> {
                        binding.loginProgressBar.visibility = View.GONE
                        showSnackBar(it.message ?: "Unknown error occurred")
                    }
                    Status.LOADING -> {
                        binding.loginProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun isLoggedIn() : Boolean{
        //default value NO_EMAIL AND NO_PASSWORD
        //use return statement to check if user is logged in
        currentEmail = sharedPref.getString(
            Constants.KEY_LOGGED_IN_EMAIL,
            Constants.NO_EMAIL
        ) ?: Constants.NO_EMAIL
        currentPassword = sharedPref.getString(
            Constants.KEY_PASSWORD,
            Constants.NO_PASSWORD
        ) ?: Constants.NO_PASSWORD
        return currentEmail != Constants.NO_EMAIL && currentPassword != Constants.NO_PASSWORD
    }


    //function is called we pop loginFragment off of backstack killing it and redirect to cemetery list fragment
    private fun redirectLogin() {

        findNavController().navigate(
            LoginFragmentDirections.actionGlobalNavigation2()
        )
    }

    private fun authenticateAPI(email: String, password: String) {
        basicAuthInterceptor.email = email
        basicAuthInterceptor.password = password
    }


}