package com.example.android_places_to_travel_app.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_places_to_travel_app.R
import com.example.android_places_to_travel_app.databinding.FragmentHomeBinding
import com.example.android_places_to_travel_app.ui.fragment.BaseFragment

class HomeFragment: BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeAdapter = HomeFragmentAdapter{ attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
        }
        binding.recyclerView.adapter = homeAdapter

        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner) { attractions ->
            homeAdapter.setData(attractions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}