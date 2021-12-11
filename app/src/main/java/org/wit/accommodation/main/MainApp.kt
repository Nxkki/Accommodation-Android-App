package org.wit.accommodation.main

import android.app.Application
import org.wit.accommodation.models.AccommodationMemStore
import org.wit.accommodation.models.AccommodationModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

//    val accommodations = ArrayList<AccommodationModel>()
val accommodations = AccommodationMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Accommodation started")
//        accommodations.add(AccommodationModel(10, "About one...", "6rms"))
//        accommodations.add(AccommodationModel(2, "About two...", "2rms"))
//        accommodations.add(AccommodationModel(3, "About three...", "3rms"))

    }
}