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
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.BaseFragment
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentRegisterBinding
import com.example.quizletclone.other.Constants
import com.example.quizletclone.other.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    val email: String? = null
    val password: String? = null
    val username: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            viewModel.register(
                email = binding.etRegisterEmail.text.toString(),
                password = binding.etRegisterPassword.text.toString(),
                userName = binding.etRegisterUsername.text.toString()
            )
        }


        viewModel.registerStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Timber.i("Success register")
                        binding.registerProgressBar.visibility = View.GONE
                        showSnackBar(it.message ?: "Successfully registered!")
                        Timber.i(" Register email is ${binding.etRegisterEmail.text.toString()}")
                        Timber.i( "Register password is ${binding.etRegisterPassword.text.toString()}")
                        Timber.i(" User name is ${binding.etRegisterUsername.text.toString()}")
                        sharedPref.edit().putString(Constants.KEY_LOGGED_IN_EMAIL, binding.etRegisterEmail.text.toString()).apply()
                        sharedPref.edit().putString(Constants.KEY_PASSWORD, binding.etRegisterPassword.text.toString()).apply()
                        sharedPref.edit().putString(Constants.KEY_LOGGED_IN_USERNAME, binding.etRegisterUsername.text.toString()).apply()
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(binding.etRegisterEmail.text.toString()))
                    }
                    Status.ERROR -> {
                        binding.registerProgressBar.visibility = View.GONE
                        showSnackBar(it.message ?: "An unknown error occurred")
                    }
                    Status.LOADING -> {
                        binding.registerProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })


    }

}