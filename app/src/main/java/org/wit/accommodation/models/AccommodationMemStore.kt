package org.wit.accommodation.models


import timber.log.Timber.i



var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class AccommodationMemStore : AccommodationStore {

    val accommodations = ArrayList<AccommodationModel>()

    override fun findAll(): List<AccommodationModel> {
        return accommodations
    }

    override fun create(accommodation: AccommodationModel) {
        accommodation.id = getId()
        accommodations.add(accommodation)
        logAll()
    }

    override fun update(accommodation: AccommodationModel) {
        var foundAccommodation: AccommodationModel? = accommodations.find { p -> p.id == accommodation.id }
        if (foundAccommodation != null) {
            foundAccommodation.price = accommodation.price
            foundAccommodation.location = accommodation.location
            foundAccommodation.rooms = accommodation.rooms
            foundAccommodation.image = accommodation.image
            foundAccommodation.lat = accommodation.lat
            foundAccommodation.lng = accommodation.lng
            foundAccommodation.zoom = accommodation.zoom
            logAll()
        }
    }


    private fun logAll() {
        accommodations.forEach { i("$it") }
    }
}