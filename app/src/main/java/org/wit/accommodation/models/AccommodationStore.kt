package org.wit.accommodation.models

interface AccommodationStore {

    fun findAll(): List<AccommodationModel>
    fun create(accommodation: AccommodationModel)
    fun update(accommodation: AccommodationModel)
    fun delete(accommodation: AccommodationModel)
    fun findOne(id: Long): AccommodationModel?
    fun findPrice(price: Int) : AccommodationModel?
    fun filteringPrice (price: Int): List<AccommodationModel>
    fun filteringType (type: String): List<AccommodationModel>



}