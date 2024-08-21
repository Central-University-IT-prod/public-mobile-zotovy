package dev.zotov.prod_app.data.repositories

import android.util.Log
import dev.zotov.prod_app.data.api.VenuesApi
import dev.zotov.prod_app.data.common.Api
import dev.zotov.prod_app.data.interfaces.MapVenueMapper
import dev.zotov.prod_app.data.interfaces.RelatedVenueMapper
import dev.zotov.prod_app.data.interfaces.VenueMapper
import dev.zotov.prod_app.data.interfaces.VenuePhotoMapper
import dev.zotov.prod_app.data.interfaces.VenuesListMapper
import dev.zotov.prod_app.data.interfaces.VenuesRepository
import dev.zotov.prod_app.data.models.LatLon
import dev.zotov.prod_app.data.models.MapVenue
import dev.zotov.prod_app.data.models.RelatedVenue
import dev.zotov.prod_app.data.models.Venue
import dev.zotov.prod_app.data.models.VenueListItem
import dev.zotov.prod_app.data.models.distance
import dev.zotov.prod_app.data.models.toLL


class VenuesRepositoryImpl(
    private val venuesListMapper: VenuesListMapper,
    private val venueMapper: VenueMapper,
    private val relatedVenueMapper: RelatedVenueMapper,
    private val venuePhotoMapper: VenuePhotoMapper,
    private val mapVenueMapper: MapVenueMapper,
) : VenuesRepository {
    private val venuesApi: VenuesApi = Api.venues

    private val cache = mutableMapOf<String, Venue>()

    private var mapVenues = mutableListOf<MapVenue>()
    private val centroids = mutableListOf<LatLon>()

    override suspend fun fetchRecommendations(
        lat: Double,
        lon: Double,
        offset: Int
    ): List<VenueListItem> {
        try {
            val response = venuesApi.getList(
                latLon = "$lat,$lon",
                limit = 10,
                offset = offset,
            )

            val body = if (response.isSuccessful) response.body() else null

            if (body == null) {
                Log.e("getRecommendations", "Failed to get venues list")
                return emptyList()
            }
            return venuesListMapper.map(body)
        } catch (e: Throwable) {
            return emptyList()
        }
    }

    override suspend fun getById(id: String): Venue? {
        try {
            // Look at cache first
            if (cache.containsKey(id)) {
                return cache[id]
            }

            // Fetch cache
            var venue = getDetailedVenue(id) ?: return null
            val related = getRelatedVenues(id)
            val photos = getPhotos(id)

            venue = venue.copy(
                related = related,
                photos = photos,
            )

            // Save venue in cache
            cache[venue.id] = venue

            return venue
        } catch (e: Throwable) {
            return null
        }
    }

    override suspend fun getNearbyVenues(lat: Double, lon: Double): List<MapVenue> {
        val point = LatLon(lat, lon)

        // прежде чем фечить новые venues, сначала смотрим, что запрашиваемая точка (новая центроида)
        // [lat], [lon] находится на достаточном удаление от других точек (750 метров, при запрашиваемой
        // радиусе 1000 метров)
        for (centroid in centroids) {
            if (centroid.distance(point) <= 0.075) {
                return mapVenues
            }
        }

        val response = venuesApi.getNearBy(point.toLL())

        val body = if (response.isSuccessful) response.body() else null

        if (body == null) {
            Log.e("getNearbyVenues", "Failed to get nearby venues $point")
            return mapVenues
        }

        mapVenues += mapVenueMapper.map(body)
        mapVenues = mapVenues.distinctBy { it.id }.toMutableList()
        centroids += point

        return mapVenues
    }

    /**
     * Возвращает детальную модельку [Venue], НО без фотографий и similarVenues
     */
    private suspend fun getDetailedVenue(id: String): Venue? {
        val response = venuesApi.getById(id)

        val body = if (response.isSuccessful) response.body() else null

        if (body == null) {
            Log.e("getDetailedVenue", "Failed to get venue by id $id")
            return null
        }

        return venueMapper.map(body)
    }

    private suspend fun getRelatedVenues(id: String): List<RelatedVenue> {
        val response = venuesApi.getRelated(id)

        val body = if (response.isSuccessful) response.body() else null

        if (body == null) {
            Log.e("getDetailedVenue", "Failed to get venue by id $id")
            return emptyList()
        }

        return relatedVenueMapper.map(body)
    }

    private suspend fun getPhotos(id: String): List<String> {
        val response = venuesApi.getPhotos(id)

        val body = if (response.isSuccessful) response.body() else null

        if (body == null) {
            Log.e("getDetailedVenue", "Failed to get venue by id $id")
            return emptyList()
        }

        return body.map { venuePhotoMapper.map(it) }
    }
}


