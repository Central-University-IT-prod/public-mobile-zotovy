package dev.zotov.prod_app.data.models

data class RelatedVenueResponse(
    val response: RelatedVenueResponseObject,
)

data class RelatedVenueResponseObject(
    val related: List<RelatedVenueResponseGroup>
)

data class RelatedVenueResponseGroup(
    val items: List<RelatedVenueResponseGroupItem>
)

data class RelatedVenueResponseGroupItem(
    val venue: RelatedVenueResponseItem,
)

data class RelatedVenueResponseItem(
    val id: String,
    val name: String,
    val location: RelatedVenueLocationItem,
    val categories: List<ResponseVenueCategory>,
)

data class RelatedVenueLocationItem(
    val address: String?,
    val lat: Double,
    val lng: Double,
    val city: String?,
    val state: String?,
    val country: String,
)
