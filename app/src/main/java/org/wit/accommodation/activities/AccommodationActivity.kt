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
    val accommodations = ArrayList<AccommodationModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.accommodationPrice.setText("0")
        Timber.plant(Timber.DebugTree())

        i("Accommodation Activity started...")

        binding.btnAdd.setOnClickListener() {
//            var accommodationPrice:Int = 0
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
                flag =false
                Snackbar
                    .make(it,"Please Enter a price", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (accommodation.location.isNotEmpty() ) {
                i("add Button Pressed: ${accommodation}")
            }
            else {
                flag = false
                Snackbar
                    .make(it,"Please Enter a location", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (accommodation.rooms.isNotEmpty()){
                i("add Button Pressed: ${accommodation}")

            }
            else {
                flag=false
                Snackbar
                    .make(it,"Please Enter a rooms", Snackbar.LENGTH_LONG)
                    .show()
            }
        if(flag)
            accommodations.add(accommodation)
            for (i in accommodations.indices)
            { i("Accommodation[$i]:${this.accommodations[i]}") }
        }

    }
}
