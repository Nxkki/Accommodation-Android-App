package org.wit.accommodation.models

interface AccommodationStore {

    fun findAll(): List<AccommodationModel>
    fun create(accommodation: AccommodationModel)
}