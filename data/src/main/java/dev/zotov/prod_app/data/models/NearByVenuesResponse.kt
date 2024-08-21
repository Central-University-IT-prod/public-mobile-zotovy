package dev.zotov.prod_app.data.models

data class NearByVenuesResponse(
    val response: NearByVenuesResponseObject,
)

data class NearByVenuesResponseObject(
    val venues: List<ResponseVenue>
)
