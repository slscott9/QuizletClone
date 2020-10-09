package com.example.quizletclone.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.databinding.DataBindingUtil
import com.example.quizletclone.R
import com.example.quizletclone.databinding.ActivityAddSetBinding
import kotlinx.android.synthetic.main.activity_add_set.*

class AddSetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_set)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.fabAddSetTerm.setOnClickListener {
           addTermDef()

        }

    }

    private fun addTermDef() {
        val parentLayout = binding.termDefContainer
        val childLayout = layoutInflater.inflate(R.layout.term_definition_item, parentLayout, false)
        parentLayout.addView(childLayout)
        binding.scrollView.fullScroll(View.FOCUS_DOWN)

    }
}