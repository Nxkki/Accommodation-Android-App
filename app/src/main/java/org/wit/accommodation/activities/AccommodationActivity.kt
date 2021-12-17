package org.wit.accommodation.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.accommodation.R
import org.wit.accommodation.databinding.ActivityAccommodationBinding
import org.wit.accommodation.helpers.showImagePicker
import org.wit.accommodation.main.MainApp
import org.wit.accommodation.models.AccommodationModel
import org.wit.accommodation.models.OnMap
import timber.log.Timber
import timber.log.Timber.i


class AccommodationActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<*>

    private lateinit var binding: ActivityAccommodationBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    val IMAGE_ADD = 1

    var accommodation = AccommodationModel()
    var edit = false
//    var location = OnMap(52.245696, -7.139102, 15f)

    lateinit var app  : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        binding.accommodationPrice.setText("0")
        var edit = false
//adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getString() )
        app = application as MainApp
        i("Accommodation Activity started...")
        if (intent.hasExtra("accommodation_edit")) {
            edit = true
            accommodation = intent.extras?.getParcelable("accommodation_edit")!!
//            binding.accommodationPrice.setText(String.valueOf(accommodation.price))
//            setText(accommodation.price)
            binding.accommodationPrice.setText("" + accommodation.price)
            binding.accommodationLocation.setText(accommodation.location)
            binding.accommodationRooms.setText(accommodation.rooms)
            binding.accommodationType.setText(accommodation.type)

//            binding.accommodationImage.isVisible
//            i("adding image ")
//            i(accommodation.image.toString())
            binding.btnAdd.setText(R.string.save_accommodation)

            Picasso.get()
                .load(accommodation.image)
                .into(binding.accommodationImage)
            if (accommodation.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_accommodation_image)
            }
        }
        binding.btnAdd.setOnClickListener() {
            var accommodationPrice: Int = 0
            var flag = false
            val accommodationLocation = binding.accommodationLocation.text.toString()
            val accommodationRooms = binding.accommodationRooms.text.toString()
            val accommodationType = binding.accommodationType.text.toString()

            //accommodation.location = binding.accommodationLocation.text.toString()
            //  accommodation.rooms = binding.accommodationRooms.text.toString()


            if (binding.accommodationPrice.text.toString()
                    .isNotEmpty() && Integer.parseInt(binding.accommodationPrice.text.toString()) > 0 &&
                accommodationLocation.isNotEmpty() && accommodationRooms.isNotEmpty()
            ) {
                accommodation.price = Integer.parseInt(binding.accommodationPrice.text.toString())
                accommodation.location = accommodationLocation
                accommodation.rooms = accommodationRooms
                accommodation.type = accommodationType

                flag = true
            }

            if (!flag) {
//                accommodations.add(accommodation)
//                i("add Button Pressed: ${accommodation.price}")
                Snackbar
                    .make(it, R.string.enter_accommodation_price,8000)

                    .show()

                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        i("in Wait")

                    }, 3000
                )

            } else {
                if (edit && flag) {
                    app.accommodations.update(accommodation.copy())
                } else {
                    app.accommodations.create(accommodation.copy())
                }
                setResult(RESULT_OK)
                finish()
            }


        }


        binding.chooseImage.setOnClickListener {
//            showImagePicker(imageIntentLauncher)
            showImagePicker(this,IMAGE_ADD)
            //  i("Select image")
        }

//        binding.accommodationOnMap.setOnClickListener {
//            i ("Set Location Pressed")
//        }
//        binding.accommodationOnMap.setOnClickListener {
//            val launcherIntent = Intent(this, MapActivity::class.java)
//            mapIntentLauncher.launch(launcherIntent)
//        }
//
//        binding.accommodationOnMap.setOnClickListener {
//            //c
//            val location = OnMap(52.245696, -7.139102, 15f)
//
//            val location2 = OnMap(location.lat, location.lng, 15f)
//            val launcherIntent = Intent(this, MapActivity::class.java)
//                .putExtra("location", location2)
//            mapIntentLauncher.launch(launcherIntent)
//        }

        binding.accommodationOnMap.setOnClickListener {
            val location = OnMap(52.245696, -7.139102, 15f)
            if (accommodation.zoom != 0f) {
                location.lat =  accommodation.lat
                location.lng = accommodation.lng
                location.zoom = accommodation.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }




//        binding.accommodationOnMap.setOnClickListener {
//            val launcherIntent = Intent(this, MapActivity::class.java)
//                .putExtra("onMap", onMap)
//            mapIntentLauncher.launch(launcherIntent)
//        }

        registerMapCallback()

    }



    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got OnMap ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<OnMap>("location")!!
                            i("OnMap == $location")
                            accommodation.lat = location.lat
                            accommodation.lng = location.lng
                            accommodation.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_ADD -> {
                //if user has chosen something
                if (data != null) {
                    i(data.getData().toString())
                    accommodation.image = data.data!!
                    i(accommodation.image.toString())
                    Picasso.get()
                        .load(accommodation.image.toString())
                        .into(binding.accommodationImage)
                    binding.chooseImage.setText(R.string.change_accommodation_image)

                }
            }

        }
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_accommodation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }

            R.id.item_delete -> {
                app.accommodations.delete(accommodation.copy())
                finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
