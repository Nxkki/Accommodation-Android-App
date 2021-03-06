package org.wit.accommodation.models


import timber.log.Timber
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
    override fun filteringPrice (price: Int): List<AccommodationModel> {
        return accommodations.filter { p -> p.price == price }

    }

    override fun filteringType(type: String): List<AccommodationModel> {
        return accommodations.filter { p -> p.type.startsWith(type) }
    }


    override fun delete(accommodation: AccommodationModel) {
        accommodations.remove(accommodation)
    }

    private fun logAll() {
        accommodations.forEach { i("$it") }
    }

    override fun findOne(id: Long) : AccommodationModel? {
        var foundAccommodation: AccommodationModel? = accommodations.find { p -> p.id == id }
        return foundAccommodation
    }
    override fun findPrice (price: Int) : AccommodationModel? {
        var foundAccommodation: AccommodationModel? = accommodations.find { p -> p.price == price }
        return foundAccommodation
    }
}