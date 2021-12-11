package org.wit.accommodation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.accommodation.databinding.CardAccommodationBinding
import org.wit.accommodation.models.AccommodationModel

class AccommodationAdapter constructor(private var accommodations: List<AccommodationModel>) :
    RecyclerView.Adapter<AccommodationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardAccommodationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val accommodation = accommodations[holder.adapterPosition]
        holder.bind(accommodation)
    }

    override fun getItemCount(): Int = accommodations.size

    class MainHolder(private val binding : CardAccommodationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: AccommodationModel) {
            binding.accommodationLocation.text = accommodation.location
            binding.rooms.text = accommodation.rooms
            binding.accommodationPrice.text =  Integer.toString(accommodation.price)
        }
    }
}