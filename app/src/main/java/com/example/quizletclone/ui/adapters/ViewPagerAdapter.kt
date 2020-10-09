package com.example.quizletclone.ui.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quizletclone.ui.logofragments.IconsFragment
import com.example.quizletclone.ui.logofragments.LogoFragment
import com.example.quizletclone.ui.logofragments.SearchLogoFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {




    override fun getItemCount(): Int  = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> LogoFragment()
            1 -> SearchLogoFragment()
            else -> IconsFragment()
        }
    }



}