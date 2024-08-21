package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.MapVenueMapper
import dev.zotov.prod_app.data.models.LatLon
import dev.zotov.prod_app.data.models.MapVenue
import dev.zotov.prod_app.data.models.NearByVenuesResponse

object MapVenueMapperImpl: MapVenueMapper {
    override fun map(raw: NearByVenuesResponse): List<MapVenue> {
        return raw.response.venues.map {
            MapVenue(
                id = it.id,
                name = it.name,
                location = LatLon(
                    lat = it.location.lat,
                    lon = it.location.lng,
                ),
                category = it.categories.firstOrNull()?.name,
            )
        }
    }
}