package com.example.quizletclone.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizletclone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragmentAdd : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_test_add, container, false)
    }

}