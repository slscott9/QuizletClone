package com.example.quizletclone.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.databinding.DataBindingUtil
import com.example.quizletclone.R
import com.example.quizletclone.databinding.ActivityAddSetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_set.*
import kotlinx.android.synthetic.main.set_item.view.*
import kotlinx.android.synthetic.main.term_definition_item.view.*

@AndroidEntryPoint
class AddSetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSetBinding

    private val views = ArrayList<View>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_set)
        binding.lifecycleOwner = this

//        setSupportActionBar(binding.toolBar)
//        supportActionBar?.setHomeButtonEnabled(true)

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

//    private fun getChildViewInput() {
//        val addTermContainer = binding.termDefContainer
//        val childCount = addTermContainer.childCount
//
//        for(i in 0 until  childCount){
//            var row = addTermContainer.getChildAt(i)
//            row.term_definition_layout.etTermInput
//        }
//    }




}