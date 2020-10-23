package com.example.quizletclone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentHomeBinding
import com.example.quizletclone.ui.adapters.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var setListAdapter: SetListAdapter
    private lateinit var folderListAdapter: FolderListAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner



        setupListAdapters()

        viewModel._allSets.observe(viewLifecycleOwner){
            setListAdapter.submitList(it)
        }

        viewModel._allFolders.observe(viewLifecycleOwner){
            folderListAdapter.submitList(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewAllListeners()



        setupViewAllListeners()
    }

    private fun setupViewAllListeners() {
        binding.tvViewAllSets.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetListFragment())
        }

        binding.tvViewAllSets.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFolderListFragment())
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