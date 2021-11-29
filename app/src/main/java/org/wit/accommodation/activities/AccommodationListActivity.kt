package org.wit.accommodation.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.accommodation.R
import org.wit.accommodation.main.MainApp

class AccommodationListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accommodation_list)
        app = application as MainApp
    }
}