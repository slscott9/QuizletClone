package com.example.quizletclone.ui.sets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentSetDetailBinding


class SetDetailFragment : Fragment() {

    private lateinit var binding: FragmentSetDetailBinding
    private lateinit var args: SetDetailFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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

        binding.setDetailToolbar.setNavigationOnClickListener {
            redirectToHomeFragment()
        }
    }

    private fun redirectToHomeFragment() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.setDetailFragment, true)
            .build()

        findNavController().navigate(SetDetailFragmentDirections.actionSetDetailFragmentToHomeFragment(), navOptions)
    }


}