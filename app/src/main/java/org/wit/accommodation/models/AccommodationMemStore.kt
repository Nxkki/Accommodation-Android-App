package org.wit.accommodation.models
import timber.log.Timber.i

class AccommodationMemStore : AccommodationStore {

    val accommodations = ArrayList<AccommodationModel>()

    override fun findAll(): List<AccommodationModel> {
        return accommodations
    }

    override fun create(accommodation: AccommodationModel) {
        accommodations.add(accommodation)
        logAll()
    }

    fun logAll() {
        accommodations.forEach{ i("${it}") }
    }
}
