package com.example.quizletclone.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentCreateFolderDialogBinding
import com.example.quizletclone.ui.folders.FolderFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_folder_dialog.*


@AndroidEntryPoint
class CreateFolderDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCreateFolderDialogBinding
    private val viewModel: FolderFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_folder_dialog, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        tvCancel.setOnClickListener {
            dismiss()
        }

//        tvOK.setOnClickListener {
//            if(binding.etFolderName.text.isNullOrEmpty()){
//                binding.tvLayoutFolderName.error = getString(R.string.folder_name_error_message)
//            }else{
//                viewModel.sendNewFolderToNetwork(
//                        binding.etFolderName.text.toString(),
//                        binding.etDescription.text.toString()
//                    )
//
//                dismiss()
//            }
//        }





    }


}