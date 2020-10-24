package com.example.quizletclone.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.BottomSheetBinding
import com.example.quizletclone.ui.create.CreateFolderDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet.*

@AndroidEntryPoint
class BottomSheetFragment() : BottomSheetDialogFragment(){

    private var fragmentView: View? = null
    private lateinit var binding: BottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false)
        binding.lifecycleOwner = viewLifecycleOwner




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.tvCreateSet.setOnClickListener {
//            findNavController().navigate(R.id.addSetFragment)
//            dismiss()
//        }




        binding.tvCreateFolder.setOnClickListener {
            val createFolderDialogFragment = CreateFolderDialogFragment()
            createFolderDialogFragment.show(parentFragmentManager, createFolderDialogFragment.tag)

        }




        //onClickListener  for create Folder that displays a dialog fragment maybe? then creates new folder takes user to the new folder
    }





//    private fun initView() {
//        binding.tvCreateSet.setOnClickListener {
//            it.findNavController().navigate(BottomSheetFragmentDirections.actionBottomSheetFragment3ToAddSetFragment4())
//        }
//        tvCreateFolder.setOnClickListener {
//        }
//        tvCreateClass.setOnClickListener {
//        }
//
//    }


}