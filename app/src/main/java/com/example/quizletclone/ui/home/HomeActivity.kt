package com.example.quizletclone.ui.home

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.quizletclone.R
import com.example.quizletclone.databinding.ActivityHomeBinding
import com.example.quizletclone.ui.create.CreateFolderDialogFragment
import com.example.quizletclone.ui.folders.FolderActivity
import com.example.quizletclone.ui.profile.ProfileActivity
import com.example.quizletclone.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.add_menu_item -> {
                    showBottomSheetDialogFragment()
//                    NavController(this).navigate(HomeActivityDirections.actionHomeActivityToAddSetFragment())
                    true

                }

                R.id.search_menu_item -> {
                    startActivity(
                        Intent(this, SearchActivity::class.java)
                    )
                    true
                }

                R.id.profile_menu_item -> {
                    startActivity(
                        Intent(this, ProfileActivity::class.java)
                    )
                    true
                }

                else -> false
            }
        }

        binding.tvViewAllFolders.setOnClickListener {
            startActivity(
                Intent(this, FolderActivity::class.java)
            )
        }
    }



    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}