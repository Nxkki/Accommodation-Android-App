package org.wit.accommodation.main

import android.app.Application
import org.wit.accommodation.models.AccommodationModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val accommodations = ArrayList<AccommodationModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Accommodation started")
    }
}