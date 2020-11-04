package com.example.quizletclone.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.setupWithNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentSearchBinding
import com.example.quizletclone.ui.adapters.SearchViewPagerAdapter
import com.example.quizletclone.ui.home.BottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
//    private val viewModel by hiltNavGraphViewModels<SearchViewModel>(R.id.navigation2)
    private lateinit var navController : NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

//        setupBottomNav()

        val pagerAdapter = SearchViewPagerAdapter(this)
        binding.viewPager2.adapter = pagerAdapter

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    //Pass Search Query to this function in viewmodel
//                    viewModel.setSearchQuery(it)
                    Timber.i(newText)
                }
                return true
            }
        })
    }

//    private fun setupBottomNav() {
//        binding.searchFragBottomNavigation.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.bn_create_menu_item -> {
//
//                    navController.navigate(R.id.bottomSheetFragment)
//                    true
//                }
//
//                R.id.bn_home_menu_item -> {
//                    navController.navigate(R.id.homeFragment3)
//                    true
//                }
//                R.id.bn_profile_menu_item -> {
//                    navController.navigate(R.id.profileFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }







}