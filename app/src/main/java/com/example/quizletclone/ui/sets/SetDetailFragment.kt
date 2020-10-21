package com.example.quizletclone.ui.sets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentSetDetailBinding
import com.example.quizletclone.ui.adapters.SetListAdapter
import com.example.quizletclone.ui.adapters.TermListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetDetailFragment : Fragment() {

    private lateinit var binding: FragmentSetDetailBinding
    private  val args: SetDetailFragmentArgs by navArgs()
    private val viewModel : SetDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setSetId(args.setId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val termListAdapter = TermListAdapter()
        binding.viewModel = viewModel

        binding.setDetailBottomNav.setupWithNavController(findNavController())


        binding.setDetailToolbar.setNavigationOnClickListener {
            redirectToHomeFragment()
        }

        viewModel.termList.observe(viewLifecycleOwner){
            termListAdapter.submitList(it)
        }

        binding.rvTermList.adapter = termListAdapter
    }

    private fun redirectToHomeFragment() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.setDetailFragment, true)
            .build()

        findNavController().navigate(SetDetailFragmentDirections.actionSetDetailFragmentToSetListFragment(), navOptions)
    }


}