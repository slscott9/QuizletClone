package com.example.quizletclone.ui.sets

import android.os.Bundle
import android.provider.VoicemailContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.example.quizletclone.R
import com.example.quizletclone.databinding.FragmentSetListBinding
import com.example.quizletclone.other.Status
import com.example.quizletclone.ui.adapters.SetListAdapter
import com.example.quizletclone.ui.adapters.SetListListener
import com.example.quizletclone.ui.search.SearchViewModel
import com.example.quizletclone.utils.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SetListFragment : Fragment() {

    private val viewModel by hiltNavGraphViewModels<SearchViewModel>(R.id.navigation2)

    private lateinit var binding: FragmentSetListBinding
    private lateinit var setListAdapter : SetListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setListAdapter = SetListAdapter(SetListListener {

        })

        return binding.root
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        Timber.i("in onViewcreated SetsFragment")
//        binding.searchResultSetListRv.adapter = setListAdapter
//
//
//        viewModel.searchList.observe(viewLifecycleOwner) {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    Timber.i("in Status success SetsFragment")
//
//                    binding.setSearchProgressBar.visibility = View.GONE
//                    it.data?.let { setList ->
//                        Timber.i(setList.toString())
//                        setListAdapter.submitList(setList)
//                    }
//                }
//                Status.ERROR -> {
//                    Timber.i("in Status success SetsFragment")
//
//                    binding.setSearchProgressBar.visibility = View.GONE
//                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
//                }
//                Status.LOADING -> {
//                    binding.setSearchProgressBar.visibility = View.VISIBLE
//                }
//            }
//        }

//        binding.searchResultSetListRv.adapter = setListAdapter
//    }


}