package com.theandroidfactory.senya

import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    val navController by lazy {
        (activity as MainActivity).navController
    }
    val viewModel: MyViewModel
        get() = (activity as MainActivity).viewModel
}