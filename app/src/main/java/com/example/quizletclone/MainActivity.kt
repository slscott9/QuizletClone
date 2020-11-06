package com.example.quizletclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.quizletclone.R
import com.example.quizletclone.ui.home.BottomSheetFragment
import com.example.quizletclone.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = Navigation.findNavController(this, R.id.navHostFragment)

        bottom_nav.setupWithNavController(navController)



        Navigation.findNavController(this, R.id.navHostFragment).addOnDestinationChangedListener() { controller, destination, arguments ->
            when(destination.id){

                R.id.homeFragment -> {
                    bottom_nav.visibility = View.VISIBLE
                }

                else -> {
                    bottom_nav.visibility = View.GONE
                }
            }
        }
    }
}

