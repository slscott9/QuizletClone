package com.example.quizletclone.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentHomeBinding
import com.example.quizletclone.ui.adapters.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var setListAdapter: SetListAdapter
    private lateinit var folderListAdapter: FolderListAdapter
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setupBottomNavListeners()
        setupViewAllListeners()
        setupListAdapters()

        viewModel._allSets.observe(viewLifecycleOwner){
            setListAdapter.submitList(it)
        }

        viewModel._allFolders.observe(viewLifecycleOwner){
            folderListAdapter.submitList(it)
        }

        return binding.root
    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun setupViewAllListeners() {
        binding.tvViewAllFolders.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFolderListFragment())
        }

        binding.tvViewAllSets.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetListFragment())
        }
    }
    private fun setupBottomNavListeners() {
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

    }

    private fun setupListAdapters(){
        setListAdapter = SetListAdapter(SetListListener {

        })

        folderListAdapter = FolderListAdapter(FolderListListener {

        })


        binding.rvSets.apply {
            adapter = setListAdapter
            layoutManager = LinearLayoutManager(requireActivity() , LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvFolderList.apply {
            adapter = folderListAdapter
            layoutManager = LinearLayoutManager(requireActivity() , LinearLayoutManager.HORIZONTAL, false)

        }

    }




}