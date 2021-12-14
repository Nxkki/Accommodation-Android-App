package org.wit.accommodation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
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
import timber.log.Timber
import timber.log.Timber.i

class AccommodationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccommodationBinding
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_ADD = 1

    var accommodation = AccommodationModel()
    var edit = false

    lateinit var app  : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        binding.accommodationPrice.setText("0")
        var edit = false

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

//            binding.accommodationImage.isVisible
//            i("adding image ")
//            i(accommodation.image.toString())
            binding.btnAdd.setText(R.string.save_accommodation)

            Picasso.get()
                .load(accommodation.image)
                .into(binding.accommodationImage)
        }
        binding.btnAdd.setOnClickListener() {
            var accommodationPrice: Int = 0
            var flag = false
            val accommodationLocation = binding.accommodationLocation.text.toString()
            val accommodationRooms = binding.accommodationRooms.text.toString()
            //accommodation.location = binding.accommodationLocation.text.toString()
            //  accommodation.rooms = binding.accommodationRooms.text.toString()


            if (binding.accommodationPrice.text.toString()
                    .isNotEmpty() && Integer.parseInt(binding.accommodationPrice.text.toString()) > 0 &&
                accommodationLocation.isNotEmpty() && accommodationRooms.isNotEmpty()
            ) {
                accommodation.price = Integer.parseInt(binding.accommodationPrice.text.toString())
                accommodation.location = accommodationLocation
                accommodation.rooms = accommodationRooms

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
        }
        return super.onOptionsItemSelected(item)
    }
        }

