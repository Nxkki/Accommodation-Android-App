package org.wit.accommodation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.accommodation.databinding.ActivityAccommodationBinding
import org.wit.accommodation.models.AccommodationModel
import timber.log.Timber
import timber.log.Timber.i

class AccommodationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccommodationBinding
    var accommodation = AccommodationModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.accommodationPrice.setText("0")
        Timber.plant(Timber.DebugTree())

        i("Accommodation Activity started...")

        binding.btnAdd.setOnClickListener() {
            var accommodationPrice:Int = 0
            if (binding.accommodationPrice.text.toString().isNotEmpty())
                accommodationPrice = Integer.parseInt(binding.accommodationPrice.text.toString())
            val accommodationLocation = binding.accommodationLocation.text.toString()
            val accommodationRooms = binding.accommodationRooms.text.toString()

            if (accommodationPrice>0 ) {
                i("add Button Pressed: $accommodationLocation")
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

        }
    }
}
