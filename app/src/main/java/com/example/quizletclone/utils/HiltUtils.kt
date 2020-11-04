package com.example.quizletclone.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

////https://itnext.io/simplifying-jetpack-navigation-between-top-level-destinations-using-dagger-hilt-3d918721d91e
//
//inline fun <reified T : ViewModel> Fragment.hiltNavGraphViewModels(@IdRes navGraphIdRes: Int) =
//    viewModels<T>(
//        ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
//        factoryProducer = { defaultViewModelProviderFactory }
//    )