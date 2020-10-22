package com.example.quizletclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_nav.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeNavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_nav)


        NavigationUI.setupWithNavController(home_activity_bottom_nav, Navigation.findNavController(this, R.id.homeNavActivity))
    }


}