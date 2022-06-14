package com.example.android_places_to_travel_app.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android_places_to_travel_app.R
import com.example.android_places_to_travel_app.arch.AttractionsViewModel
import com.example.android_places_to_travel_app.data.Attraction
import com.example.android_places_to_travel_app.data.AttractionsResponse
import com.example.android_places_to_travel_app.ui.fragment.AttractionDetailFragment
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    val viewModel: AttractionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.init(this)
        viewModel.selectedLocationLiveData.observe(this) { attraction ->
            val gmmIntentUri = Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=10&q=${attraction.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        viewModel.attractionFactsLiveData.observe(this) { attraction ->
            val stringBuilder = StringBuilder("")
            attraction.facts.forEach {
                stringBuilder.append("\u2022 $it")
                stringBuilder.append("\n\n")
            }
            val message =
                stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n\n"))

            AlertDialog.Builder(this)
                .setTitle("${attraction.title} Facts")
                .setMessage(message)
                .setPositiveButton("Ok") { dialog, which ->
                    // run your code
                }
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}