package com.example.quizletclone.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentAddSetBinding
import com.example.quizletclone.other.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.term_definition_item.view.*
import timber.log.Timber

@AndroidEntryPoint
class AddSetFragment : Fragment() {

    private lateinit var binding: FragmentAddSetBinding
    private val viewModel: AddSetViewModel by viewModels()
    private val termList = ArrayList<Term>()
    private val termLayoutList = ArrayList<View>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_set, container, false)
        binding.lifecycleOwner = viewLifecycleOwner



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddSetTerm.setOnClickListener {
            addTermDef()

        }
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.save_set_item -> {
                    getTerms()
                    val setName = binding.etSetTitle.text.toString()
                    Timber.i(termList.toString())
                    Timber.i(termLayoutList.size.toString())
                    viewModel.sendNewSetToNetwork(
                        setName = setName,
                        termList
                    )


                    true
                }
                else -> false
            }
        }

        viewModel.addSetStatus.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    binding.addSetProgressBar.visibility = View.GONE
                    redirectToSetDetail()

                }
                Status.LOADING -> {
                    binding.addSetProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(requireActivity(), "Failed add set", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun addTermDef() {
        val parentLayout = binding.termDefContainer
        val childLayout = layoutInflater.inflate(R.layout.term_definition_item, parentLayout, false)
        termLayoutList.add(childLayout)
        parentLayout.addView(childLayout)

        binding.scrollView.fullScroll(View.FOCUS_DOWN)

    }

    private fun getTerms() {
        termLayoutList.forEach {

            if(!it.etTermInput.text.isNullOrEmpty() &&
                !it.etDefinitionInput.text.isNullOrEmpty()){

                termList.add(Term(
                    it.etTermInput.text.toString(),
                    it.etDefinitionInput.text.toString()
                ))
            }
        }
    }

    private fun redirectToSetDetail() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.addSetFragment, true)
            .build()
        findNavController().navigate(AddSetFragmentDirections.actionAddSetFragmentToSetDetailFragment())
    }

    data class Term(
        val term: String,
        val answer : String
    )


}