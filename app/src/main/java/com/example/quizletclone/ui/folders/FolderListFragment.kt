package com.example.quizletclone.ui.folders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentFolderBinding
import com.example.quizletclone.ui.create.CreateFolderDialogFragment
import com.example.quizletclone.ui.login.LoginFragmentDirections


class FolderListFragment : Fragment() {

    private lateinit var binding: FragmentFolderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_folder, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.folderActivityToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add_folder_menu_item -> {
                    val createFolderDialogFragment = CreateFolderDialogFragment()
                    createFolderDialogFragment.show(parentFragmentManager, createFolderDialogFragment.tag)
                    true
                }
                else -> false
            }
        }


        binding.folderActivityToolbar.setNavigationOnClickListener {

        }

        return binding.root



    }

    private fun redirectLogin() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.folderListFragment, true) //kills login fragment so when back button is pressed from cemetery list we do not go back to login fragment
            .build()
        findNavController().navigate(
            Folder.actionLoginFragmentToHomeFragment(), navOptions
        )
    }


}