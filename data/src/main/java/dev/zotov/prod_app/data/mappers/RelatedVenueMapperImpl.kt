package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.RelatedVenueMapper
import dev.zotov.prod_app.data.models.RelatedVenue
import dev.zotov.prod_app.data.models.RelatedVenueResponse

object RelatedVenueMapperImpl : RelatedVenueMapper {
    override fun map(raw: RelatedVenueResponse): List<RelatedVenue> {
        val venues = mutableListOf<RelatedVenue>()

        raw.response.related.forEach { group ->
            group.items.forEach { item ->
                venues.add(
                    item.venue.let {
                        RelatedVenue(
                            id = it.id,
                            name = it.name,
                            category = it.categories.firstOrNull()?.name,
                            location = it.location.address ?: it.location.city ?: it.location.state
                            ?: it.location.country
                        )
                    }
                )
            }
        }

        return venues
    }
}