package com.example.android_places_to_travel_app.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_places_to_travel_app.data.Attraction

class AttractionsViewModel: ViewModel() {

    private val repository = AttractionsRepository()

    // HomeFragment
    val attractionListLiveData = MutableLiveData<List<Attraction>>()

    // AttractionDetailFragment
    val attractionDetailLiveData = MutableLiveData<Attraction>()

    // MainActivity Map Intent
    val selectedLocationLiveData = MutableLiveData<Attraction>()

    // MainActivity dialog facts
    val attractionFactsLiveData = MutableLiveData<Attraction>()

    fun init(context: Context) {
        val attractionsList = repository.parseAttractions(context)
        attractionListLiveData.postValue(attractionsList)
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionListLiveData.value?.find {
            it.id == attractionId
        } ?: return

        attractionDetailLiveData.postValue(attraction)
    }
}