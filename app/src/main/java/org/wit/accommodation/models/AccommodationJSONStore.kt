package org.wit.accommodation.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.accommodation.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "accommodations.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<AccommodationModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AccommodationJSONStore(private val context: Context) : AccommodationStore {

    var accommodations = mutableListOf<AccommodationModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AccommodationModel> {
        logAll()
        return accommodations
    }

    override fun create(accommodation: AccommodationModel) {
        accommodation.id = generateRandomId()
        accommodations.add(accommodation)
        serialize()
    }


    override fun update(accommodation: AccommodationModel) {
        var foundAccommodation: AccommodationModel? =
            accommodations.find { p -> p.id == accommodation.id }
        if (foundAccommodation != null) {
            foundAccommodation.price = accommodation.price
            foundAccommodation.location = accommodation.location
            foundAccommodation.rooms = accommodation.rooms
            foundAccommodation.image = accommodation.image
            foundAccommodation.lat = accommodation.lat
            foundAccommodation.lng = accommodation.lng
            foundAccommodation.zoom = accommodation.zoom
            logAll()
            serialize()

        }
    }

    override fun delete(accommodation: AccommodationModel) {
        accommodations.remove(accommodation)
        serialize()
    }
//    override fun update(accommodation: AccommodationModel) {
//        // todo
//    }



    private fun serialize() {
        val jsonString = gsonBuilder.toJson(accommodations, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        accommodations = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        accommodations.forEach { Timber.i("$it") }
    }
    override fun findOne(id: Long) : AccommodationModel? {
        var foundAccommodation: AccommodationModel? = accommodations.find { c -> c.id == id }
        return foundAccommodation
    }
    fun filteringPrice (price: Int): List<AccommodationModel> {
        return accommodations.filter { p -> p.price == price }

    }

    override fun findPrice (price: Int) : AccommodationModel? {
        var foundAccommodation: AccommodationModel? = accommodations.find { p -> p.price == price }
        return foundAccommodation
    }

}


class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}