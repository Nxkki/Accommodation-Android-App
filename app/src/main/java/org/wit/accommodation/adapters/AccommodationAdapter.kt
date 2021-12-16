package org.wit.accommodation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.accommodation.databinding.CardAccommodationBinding
import org.wit.accommodation.models.AccommodationModel

class AccommodationAdapter constructor(private var accommodations: List<AccommodationModel>,
                                   private val listener: AccommodationListener) :
    RecyclerView.Adapter<AccommodationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardAccommodationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val accommodation = accommodations[holder.adapterPosition]
        holder.bind(accommodation, listener)
    }

    override fun getItemCount(): Int = accommodations.size

    class MainHolder(private val binding : CardAccommodationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: AccommodationModel, listener: AccommodationListener) {
            binding.accommodationLocation.text = accommodation.location
            binding.accommodationRooms.text = accommodation.rooms
            binding.accommodationPrice.text =  Integer.toString(accommodation.price)
            binding.accommodationType.text =  accommodation.type

            Picasso.get().load(accommodation.image).resize(200,200).into(binding.imageIcon)

            binding.root.setOnClickListener { listener.onAccommodationClick(accommodation) }
        }
    }
}

interface AccommodationListener {
    fun onAccommodationClick(accommodation: AccommodationModel)
}