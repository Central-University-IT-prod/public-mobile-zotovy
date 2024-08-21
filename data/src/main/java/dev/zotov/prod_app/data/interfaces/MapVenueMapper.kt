package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.MapVenue
import dev.zotov.prod_app.data.models.NearByVenuesResponse

interface MapVenueMapper {

    fun map(raw: NearByVenuesResponse): List<MapVenue>
}