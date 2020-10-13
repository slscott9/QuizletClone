package com.example.quizletclone.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentSetsBinding
import com.example.quizletclone.other.Status
import com.example.quizletclone.ui.adapters.SetListAdapter
import com.example.quizletclone.ui.adapters.SetListListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SetsFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSetsBinding
    private lateinit var setListAdapter : SetListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sets, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setListAdapter = SetListAdapter(SetListListener {

        })



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.i("in onViewcreated SetsFragment")
        binding.searchResultSetListRv.adapter = setListAdapter


        lifecycleScope.launch {
            viewModel.searchList.collect{
                when(it.status){
                    Status.SUCCESS -> {
                        Timber.i("in Status success SetsFragment")

                        binding.setSearchProgressBar.visibility = View.GONE
                        it.data?.let { setList ->
                            Timber.i(setList.toString())
                            setListAdapter.submitList(setList)
                        }
                    }
                    Status.ERROR -> {
                        Timber.i("in Status success SetsFragment")

                        binding.setSearchProgressBar.visibility = View.GONE
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.setSearchProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        }

    }


}