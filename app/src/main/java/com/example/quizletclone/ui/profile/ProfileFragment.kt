package com.example.quizletclone.ui.profile

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentProfileBinding
import com.example.quizletclone.other.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    @Inject
    lateinit var sharedPref: SharedPreferences

    private lateinit var navController: NavController
    private lateinit var binding : FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()


        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        //Set shared prefs to no email and no password so login fragment knows if user is logged in or not
        sharedPref.edit().putString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL).apply() //no email and no password in shared prefs when user logs out. Redirect to login fragment
        sharedPref.edit().putString(Constants.KEY_PASSWORD, Constants.NO_PASSWORD).apply()

        //pop everything except login fragment
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.homeLoginFragment, true)
            .build()
        navController.navigate(
            R.id.loginNavigation

        )
    }



}