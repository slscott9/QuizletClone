package com.example.quizletclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.quizletclone.R
import com.example.quizletclone.ui.home.BottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = Navigation.findNavController(this, R.id.navHostFragment)

        bottom_nav.setupWithNavController(navController)

//        bottom_nav.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.create_menu_item -> {
//                    val bottomSheetFragment = BottomSheetFragment()
//                    bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
//                    true
//
//                }
//                R.id.search_menu_item -> {
//                    navController.navigate(R.id.searchFragment)
//                    true
//                }
//                R.id.profile_menu_item -> {
//                    navController.navigate(R.id.profileFragment)
//                    true
//                }
//                R.id.home_menu_item -> {
//                    navController.navigate(R.id.homeFragment)
//                    true
//                }
//                else -> false
//            }
//        }



        Navigation.findNavController(this, R.id.navHostFragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){

                R.id.homeFragment -> {
                    bottom_nav.visibility = View.VISIBLE


                }

//                R.id.bottomSheetFragment -> {
//                        val bottomSheetFragment = BottomSheetFragment()
//                    bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
//
//                }

            }
        }
    }
}

