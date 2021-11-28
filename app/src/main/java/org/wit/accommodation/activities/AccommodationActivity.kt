package org.wit.placemark.activities

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

        Timber.plant(Timber.DebugTree())

        i("Accommodation Activity started...")

        binding.btnAdd.setOnClickListener() {
            accommodation.location = binding.accommodationLocation.text.toString()
            if (accommodation.location.isNotEmpty()) {
                i("add Button Pressed: $accommodation.location")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a location", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}