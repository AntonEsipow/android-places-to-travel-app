package com.example.android_places_to_travel_app.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.android_places_to_travel_app.R
import com.example.android_places_to_travel_app.data.Attraction
import com.example.android_places_to_travel_app.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment: BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    private val saveArgs: AttractionDetailFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find {it.id == saveArgs.attractionId}!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = attraction.title
        Picasso.get()
            .load(attraction.image_url)
            .error(R.drawable.error_icon)
            .into(binding.headerImageView)
        binding.descriptionTextView.text = attraction.description
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.numberOfFactsTextView.text = "${attraction.facts.size} facts"
        binding.numberOfFactsTextView.setOnClickListener {
            val stringBuilder = StringBuilder("")
            attraction.facts.forEach {
                stringBuilder.append("\u2022 $it")
                stringBuilder.append("\n\n")
            }
            val message = stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n\n"))

            AlertDialog.Builder(requireContext())
                .setTitle("${attraction.title} Facts")
                .setMessage(message)
                .setPositiveButton("Ok") { dialog, which ->
                    // run your code
                }
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attractions_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuItemLocation -> {
                val gmmIntentUri = Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=10&q=${attraction.title}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}