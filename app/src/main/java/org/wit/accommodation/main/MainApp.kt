package org.wit.accommodation.main

import android.app.Application
import org.wit.accommodation.models.AccommodationJSONStore
import org.wit.accommodation.models.AccommodationMemStore
import org.wit.accommodation.models.AccommodationModel
import org.wit.accommodation.models.AccommodationStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

//    listActi:
//    theres a line through accommodationClick
//    val accommodations = ArrayList<AccommodationModel>()
//val accommodations = AccommodationMemStore()
lateinit var accommodations: AccommodationStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        accommodations = AccommodationJSONStore(applicationContext)

        i("Accommodation started")


    }
}