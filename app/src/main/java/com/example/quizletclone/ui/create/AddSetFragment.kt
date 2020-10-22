package com.example.quizletclone.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.data.dto.NetworkSet
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
    private lateinit var parentLayout: LinearLayout


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

        parentLayout = binding.termDefContainer
        termLayoutList.add(parentLayout.term_definition_layout) //add the initial layout that is already there from the include tag. if not it will be skipped

        binding.fabAddSetTerm.setOnClickListener {
            addTermDef()

        }
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.save_set_item -> {

                    if(binding.etSetTitle.text.isNullOrBlank()){
                        Toast.makeText(requireActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show()
                    }else{
                        getTerms()
                        viewModel.insertSet(
                            binding.etSetTitle.text.toString(),
                            termList = termList
                        )
                    }
                    true
                }
                else -> false
            }
        }

        viewModel.addSetStatus.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    binding.addSetProgressBar.visibility = View.GONE
                    Timber.i("set id passed is ${viewModel.setId.toString()}")
//                    redirectToSetDetail(viewModel.setId)

                }
                Status.LOADING -> {
                    binding.addSetProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun addTermDef() {
        val childLayout = layoutInflater.inflate(R.layout.term_definition_item, parentLayout, false)
        termLayoutList.add(childLayout) //add card view with term and definition layout to list
        Timber.i(termLayoutList.size.toString())
        parentLayout.addView(childLayout)
        binding.scrollView.fullScroll(View.FOCUS_DOWN)

    }

    private fun getTerms() { //iterate through list of card views with term and def, extract each input if not blank

        termLayoutList.forEach {
            if(!it.etTermInput.text.isNullOrBlank() &&
                !it.etDefinitionInput.text.isNullOrBlank()){

                Timber.i( it.etTermInput.text.toString(),
                    it.etDefinitionInput.text.toString())
                termList.add(Term(
                    it.etTermInput.text.toString(),
                    it.etDefinitionInput.text.toString()
                ))
            }
        }
    }

//    private fun redirectToSetDetail(setId: Long) {
//        val navOptions = NavOptions.Builder()
//            .setPopUpTo(R.id.addSetFragment, true)
//            .build()
//        findNavController().navigate(AddSetFragmentDirections.actionAddSetFragmentToSetDetailFragment(setId), navOptions)
//    }

    data class Term(
        val term: String,
        val answer : String
    )


}