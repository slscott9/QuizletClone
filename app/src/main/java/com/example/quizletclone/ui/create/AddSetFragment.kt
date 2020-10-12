package com.example.quizletclone.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentAddSetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSetFragment : Fragment() {

    private lateinit var binding: FragmentAddSetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_set, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.fabAddSetTerm.setOnClickListener {
            addTermDef()

        }

        return binding.root
    }

    private fun addTermDef() {
        val parentLayout = binding.termDefContainer
        val childLayout = layoutInflater.inflate(R.layout.term_definition_item, parentLayout, false)
        parentLayout.addView(childLayout)
        binding.scrollView.fullScroll(View.FOCUS_DOWN)

    }


}