package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.Venue
import dev.zotov.prod_app.data.models.VenueDetailResponse

interface VenueMapper {
    fun map(raw: VenueDetailResponse, photos: List<String> = emptyList()): Venue
}