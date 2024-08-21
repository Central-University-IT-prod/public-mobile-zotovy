package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.MapVenue
import dev.zotov.prod_app.data.models.Venue
import dev.zotov.prod_app.data.models.VenueListItem

interface VenuesRepository {
    suspend fun fetchRecommendations(lat: Double, lon: Double, offset: Int = 0): List<VenueListItem>

    suspend fun getById(id: String): Venue?

    suspend fun getNearbyVenues(lat: Double, lon: Double): List<MapVenue>
}