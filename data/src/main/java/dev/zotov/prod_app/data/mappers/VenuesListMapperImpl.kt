package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.VenuesListMapper
import dev.zotov.prod_app.data.models.VenueListItem
import dev.zotov.prod_app.data.models.VenuesListResponse
import dev.zotov.prod_app.data.utils.roundToNearest

object VenuesListMapperImpl: VenuesListMapper {
    override fun map(raw: VenuesListResponse): List<VenueListItem> {
        if (raw.response.group.results == null) {
            return emptyList()
        }

        return raw.response.group.results.map { it ->
            VenueListItem(
                id = it.venue.id,
                photoUrl = it.photo.let {
                    if (it != null) {
                        "${it.prefix}original${it.suffix}"
                    } else {
                        null
                    }
                },
                name = it.venue.name,
                category = it.venue.categories.firstOrNull()?.name,
                location = it.venue.location.address ?: it.venue.location.city ?: it.venue.location.state ?: it.venue.location.country,
                distance = "${it.venue.location.distance.roundToNearest(2)} метров",
                photos = it.photos.let {
                    if (it != null) {
                        val photos = mutableListOf<String>()
                        it.groups.forEach { group ->
                            group.items.forEach { photo ->
                                photos.add("${photo.prefix}original${photo.suffix}")
                            }
                        }
                        photos
                    } else {
                        emptyList()
                    }
                }
            )
        }
    }
}