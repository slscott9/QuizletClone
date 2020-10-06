package com.example.quizletclone.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.BaseFragment
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentHomeLoginBinding
import com.example.quizletclone.ui.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_login.*


@AndroidEntryPoint
class HomeLoginFragment : BaseFragment(R.layout.fragment_home_login) {

    private lateinit var binding : FragmentHomeLoginBinding


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

        viewPager.adapter = ViewPagerAdapter()



        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(HomeLoginFragmentDirections.actionHomeLoginFragmentToLoginFragment())
        }

        binding.tvLogin.setOnClickListener {
            findNavController().navigate(HomeLoginFragmentDirections.actionHomeLoginFragmentToRegisterFragment())
        }


    }








}