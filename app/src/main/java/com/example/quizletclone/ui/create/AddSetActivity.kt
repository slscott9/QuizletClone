package com.example.quizletclone.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizletclone.R
import com.example.quizletclone.databinding.ActivityAddSetBinding

class AddSetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_set)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setHomeButtonEnabled(true)

    }
}