package dev.zotov.prod_app.data.models

data class VenueDetailResponse(
    val response: VenueDetailResponseObject
)

data class VenueDetailResponseObject(
    val venue: VenueObject
)

data class VenueObject(
    val id: String,
    val name: String,
    val location: VenueResponseLocation,
    val categories: List<ResponseVenueCategory>,
    val contact: VenueResponseContacts?,
    val bestPhoto: VenuePhoto?,
)

data class VenueResponseLocation(
    val formattedAddress: List<String>,
    val lng: Double,
    val lat: Double,
)

data class VenueResponseContacts(
    val phone: String?,
    val formattedPhone: String?,
    val facebookName: String?,
    val twitter: String?,
    val instagram: String?,
)

data class VenuePhoto(
    val prefix: String,
    val suffix: String,
)

