package com.example.android_places_to_travel_app.arch

import android.content.Context
import com.example.android_places_to_travel_app.R
import com.example.android_places_to_travel_app.data.Attraction
import com.example.android_places_to_travel_app.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AttractionsRepository {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): List<Attraction> {
        val textFromFile = context.resources
            .openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions
    }
}