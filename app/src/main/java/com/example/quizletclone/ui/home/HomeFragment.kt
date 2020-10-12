package com.example.quizletclone.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.add_menu_item -> {
                    showBottomSheetDialogFragment()
//                    NavController(this).navigate(HomeActivityDirections.actionHomeActivityToAddSetFragment())
                    true

                }

                R.id.search_menu_item -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
                    true
                }

                R.id.profile_menu_item -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
                    true
                }

                else -> false
            }
        }

        binding.tvViewAllFolders.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFolderListFragment())
        }

        binding.tvViewAllSets.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetListFragment())
        }
        return binding.root
    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }


}