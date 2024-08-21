package dev.zotov.prod_app.data.models

data class VenuesListResponse(
    val response: VenuesListResponseObject
)

data class VenuesListResponseObject(
    val group: VenuesListResponseGroup
)

data class VenuesListResponseGroup(
    val results: List<VenuesListResponseResult>?
)

data class VenuesListResponseResult(
    val venue: ResponseVenue,
    val photo: ResponseVenuePhoto?,
    val photos: ResponseVenuePhotos?,
)

data class ResponseVenue(
    val id: String,
    val name: String,
    val location: ResponseVenueLocation,
    val categories: List<ResponseVenueCategory>
)

data class ResponseVenueLocation(
    val address: String?,
    val lat: Double,
    val lng: Double,
    val distance: Int,
    val city: String?,
    val state: String?,
    val country: String,
)

data class ResponseVenueCategory(
    val name: String,
)

data class ResponseVenuePhoto(
    val prefix: String,
    val suffix: String,
)

data class ResponseVenuePhotos(
    val groups: List<ResponseVenuePhotosGroup>
)

data class ResponseVenuePhotosGroup(
    val items: List<ResponseVenuePhoto>
)
