package org.wit.accommodation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.accommodation.databinding.ActivityAccommodationBinding
import timber.log.Timber
import timber.log.Timber.i


class AccommodationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccommodationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_accommodation)
        binding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Timber.plant(Timber.DebugTree())

        i("Accommodation Activity started..")

//        binding.btnAdd.setOnClickListener() {
//            i("add Button Pressed")
//        }
        binding.btnAdd.setOnClickListener() {
            val accommodationLocation = binding.accommodationLocation.text.toString()
            if (accommodationLocation.isNotEmpty()) {
                i("add Button Pressed: $accommodationLocation")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a Location", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}