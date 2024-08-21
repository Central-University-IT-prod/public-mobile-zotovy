package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.VenueListItem
import dev.zotov.prod_app.data.models.VenuesListResponse

interface VenuesListMapper {

    fun map(raw: VenuesListResponse): List<VenueListItem>
}