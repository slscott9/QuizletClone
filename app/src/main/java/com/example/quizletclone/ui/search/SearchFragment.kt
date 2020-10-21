package com.example.quizletclone.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.setupWithNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentSearchBinding
import com.example.quizletclone.ui.adapters.SearchViewPagerAdapter
import com.example.quizletclone.ui.home.BottomSheetFragment
import com.example.quizletclone.utils.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by hiltNavGraphViewModels<SearchViewModel>(R.id.navigation2)


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

        setupNavigation()

        val pagerAdapter = SearchViewPagerAdapter(this)
        binding.viewPager2.adapter = pagerAdapter

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    //Pass Search Query to this function in viewmodel
                    viewModel.setSearchQuery(it)
                    Timber.i(newText)
                }
                return true
            }
        })
    }

    fun setupDialogFragment() {
        binding.searchFragBottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.add_set_menu_item -> {
                    showBottomSheetDialogFragment()
                    true
                }
                else -> false
            }
        }
    }


    fun setupNavigation() {
        binding.searchFragBottomNavigation.setupWithNavController(findNavController())

    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }


}