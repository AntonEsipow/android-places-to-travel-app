package com.example.android_places_to_travel_app.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_places_to_travel_app.R
import com.example.android_places_to_travel_app.data.Attraction
import com.example.android_places_to_travel_app.databinding.ViewHolderAttractionBinding
import com.squareup.picasso.Picasso

class HomeFragmentAdapter(
    private val onClickedBack: () -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val attractions = ArrayList<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttractionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AttractionViewHolder).onBind(attractions[position], onClickedBack)
    }

    override fun getItemCount(): Int {
        return attractions.size
    }

    fun setData(attractions: List<Attraction>) {
        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()
    }

    inner class AttractionViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_attraction, parent, false)
    ) {

        private val binding = ViewHolderAttractionBinding.bind(itemView)

        fun onBind(attraction: Attraction, onClicked: () -> Unit) {
            binding.titleTextView.text = attraction.title
            Picasso.get()
                .load(attraction.image_url)
                .error(R.drawable.error_icon)
                .into(binding.headerImageView)
            binding.monthToVisitTextView.text = attraction.months_to_visit

            binding.root.setOnClickListener {
                onClicked()
            }
        }
    }
}