package com.theandroidfactory.senya

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    val navController by lazy {
        (activity as MainActivity).navController
    }
    val attractions: List<Attraction>
        get() = (activity as MainActivity).attractions
}