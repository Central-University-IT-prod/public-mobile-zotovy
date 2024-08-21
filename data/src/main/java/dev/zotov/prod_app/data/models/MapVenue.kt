package dev.zotov.prod_app.data.models

data class MapVenue(
    val id: String,
    val name: String,
    val location: LatLon,
    val category: String?,
)

