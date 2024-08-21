package dev.zotov.prod_app.data.models

data class Venue(
    val id: String,
    val name: String,
    val bestPhoto: String?,
    val photos: List<String>,
    val location: VenueLocation,
    val contacts: VenueContacts?,
    val categories: List<String>,
    val related: List<RelatedVenue>
)

data class VenueLocation(
    val lat: Double,
    val lon: Double,
    val formattedAddress: String,
)

data class VenueContacts(
    val phone: String?,
    val formattedPhone: String?,
    val facebook: String?,
    val twitter: String?,
    val instagram: String?,
)
