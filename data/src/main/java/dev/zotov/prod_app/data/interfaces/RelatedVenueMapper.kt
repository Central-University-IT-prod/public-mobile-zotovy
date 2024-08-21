package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.RelatedVenue
import dev.zotov.prod_app.data.models.RelatedVenueResponse

interface RelatedVenueMapper {
    fun map(raw: RelatedVenueResponse): List<RelatedVenue>
}