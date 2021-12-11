package org.wit.accommodation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.accommodation.databinding.ActivityAccommodationBinding
import org.wit.accommodation.main.MainApp
import org.wit.accommodation.models.AccommodationModel
import timber.log.Timber
import timber.log.Timber.i

class AccommodationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccommodationBinding
    var accommodation = AccommodationModel()
    lateinit var app  : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.accommodationPrice.setText("0")

        app = application as MainApp
        i("Accommodation Activity started...")

        binding.btnAdd.setOnClickListener() {
            var accommodationPrice:Int = 0
            if (binding.accommodationPrice.text.toString().isNotEmpty())
                accommodationPrice = Integer.parseInt(binding.accommodationPrice.text.toString())
            val accommodationLocation = binding.accommodationLocation.text.toString()
            val accommodationRooms = binding.accommodationRooms.text.toString()

            if (accommodationPrice>0 ) {
                i("add Button Pressed: $accommodationLocation")
            accommodation.price = 0
            if (binding.accommodationPrice.text.toString().isNotEmpty())
                accommodation.price = Integer.parseInt(binding.accommodationPrice.text.toString())
            accommodation.location = binding.accommodationLocation.text.toString()
            accommodation.rooms = binding.accommodationRooms.text.toString()

var flag=true

            if (accommodation.price>0 ) {
//                accommodations.add(accommodation)
                i("add Button Pressed: ${accommodation.price}")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a price", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (accommodationLocation.isNotEmpty() ) {
                i("add Button Pressed: $accommodationLocation")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a location", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (accommodationRooms.isNotEmpty()){
                i("add Button Pressed: $accommodationRooms")

            }
            else {
                Snackbar
                    .make(it,"Please Enter a rooms", Snackbar.LENGTH_LONG)
                    .show()
            }
        if(flag)
           app.accommodations.add(accommodation.copy())
            for (i in app.accommodations.indices) {
                i("Accommodation[$i]:${this.app.accommodations[i]}")
            }
                setResult(RESULT_OK)
                finish()
        }

        }
    }
}
