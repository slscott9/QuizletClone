package com.example.quizletclone.ui.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.BaseFragment
import com.example.quizletclone.R
import com.example.quizletclone.data.remote.BasicAuthInterceptor
import com.example.quizletclone.databinding.FragmentHomeLoginBinding
import com.example.quizletclone.other.Constants
import com.example.quizletclone.ui.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_login.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeLoginFragment : BaseFragment(R.layout.fragment_home_login) {

    private lateinit var binding : FragmentHomeLoginBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_login, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerAdapter(this)


        if(isLoggedIn()){
            authenticateAPI(currentEmail ?: "", currentPassword ?: "")
            findNavController().navigate(
                R.id.navigation2
            )
        }




        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(HomeLoginFragmentDirections.actionHomeLoginFragmentToRegisterFragment())
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(HomeLoginFragmentDirections.actionHomeLoginFragmentToLoginFragment())
        }


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





    private fun authenticateAPI(email: String, password: String) {
        basicAuthInterceptor.email = email
        basicAuthInterceptor.password = password
    }




}