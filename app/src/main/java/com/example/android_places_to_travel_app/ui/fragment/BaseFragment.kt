package com.example.android_places_to_travel_app.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.android_places_to_travel_app.arch.AttractionsViewModel
import com.example.android_places_to_travel_app.data.Attraction
import com.example.android_places_to_travel_app.ui.MainActivity

abstract class BaseFragment: Fragment() {

    protected val navController: NavController by lazy {
        (activity as MainActivity).navController
    }

    protected val activityViewModel: AttractionsViewModel
        get() = (activity as MainActivity).viewModel
}