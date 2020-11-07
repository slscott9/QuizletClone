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
import androidx.navigation.fragment.navArgs
import com.example.quizletclone.BaseFragment
import com.example.quizletclone.R
import com.example.quizletclone.data.remote.BasicAuthInterceptor
import com.example.quizletclone.databinding.FragmentLoginBinding
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Constants.KEY_LOGGED_IN_EMAIL
import com.example.quizletclone.other.Constants.KEY_LOGGED_IN_USERNAME
import com.example.quizletclone.other.Constants.KEY_PASSWORD
import com.example.quizletclone.other.Constants.NO_PASSWORD
import com.example.quizletclone.other.Constants.NO_USERNAME
import com.example.quizletclone.other.Status
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.http.HttpMethod
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var basicAuthInterceptor: BasicAuthInterceptor

    private var currentUsername: String ? = null
    private var currentPassword: String ? = null
    private val args: LoginFragmentArgs by navArgs()


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
            authenticateAPI(currentUsername ?: "", currentPassword ?: "")
            redirectLogin()
        }

        binding.btnLogin.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            currentUsername = userName
            currentPassword = password

            viewModel.login(userName, password)
        }

        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status){
                    Status.SUCCESS -> {

                        binding.loginProgressBar.visibility = View.GONE
                        Timber.i(currentUsername)
                        sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME, currentUsername).apply()
                        sharedPref.edit().putString(KEY_LOGGED_IN_EMAIL, args.email).apply()
                        sharedPref.edit().putString(KEY_PASSWORD, currentPassword).apply()

                        authenticateAPI(currentUsername?: "" , currentPassword ?: "")
                        redirectLogin()
                    }
                    Status.ERROR -> {
                        binding.loginProgressBar.visibility = View.GONE
                        Timber.i(it.data.toString())


                        showSnackBar(it.message.toString())
//
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
        currentUsername = sharedPref.getString(
            KEY_LOGGED_IN_USERNAME,
            NO_USERNAME
        ) ?: NO_USERNAME
        currentPassword = sharedPref.getString(
            KEY_PASSWORD,
            NO_PASSWORD
        ) ?: NO_PASSWORD
        return currentUsername != NO_USERNAME && currentPassword != NO_PASSWORD
    }


    //function is called we pop loginFragment off of backstack killing it and redirect to cemetery list fragment
    private fun redirectLogin() {

        val option = NavOptions.Builder()
            .setPopUpTo(R.id.homeFragment, true)
            .build()
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(), option
        )
    }

    private fun authenticateAPI(userName: String, password: String) {

        basicAuthInterceptor.userName = userName
        basicAuthInterceptor.password = password
    }


}