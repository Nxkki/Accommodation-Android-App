package org.wit.accommodation.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccommodationModel(var id: Long = 0,
                              var price: Int = 0,
                              var location: String = "",
                              var rooms: String = "",
                              var type: String = "",
                              var image: Uri = Uri.EMPTY,
                              var lat : Double = 0.0,
                              var lng: Double = 0.0,
                              var zoom: Float = 0f) : Parcelable
@Parcelize
data class OnMap(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
