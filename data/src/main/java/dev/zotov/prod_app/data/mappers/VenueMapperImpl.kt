package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.VenueMapper
import dev.zotov.prod_app.data.models.Venue
import dev.zotov.prod_app.data.models.VenueContacts
import dev.zotov.prod_app.data.models.VenueDetailResponse
import dev.zotov.prod_app.data.models.VenueLocation

object VenueMapperImpl : VenueMapper {
    override fun map(raw: VenueDetailResponse, photos: List<String>): Venue {
        return raw.response.venue.let {
            Venue(
                id = it.id,
                name = it.name,
                bestPhoto = it.bestPhoto?.let { photo ->
                    "${photo.prefix}original${photo.suffix}"
                },
                photos = photos,
                location = it.location.let { location ->
                    VenueLocation(
                        lat = location.lat,
                        lon = location.lng,
                        formattedAddress = location.formattedAddress.joinToString(separator = ", ")
                    )
                },
                contacts = it.contact?.let { contacts ->
                    if (contacts.phone == null && contacts.facebookName == null && contacts.twitter == null && contacts.instagram == null) {
                        null
                    } else {
                        VenueContacts(
                            phone = contacts.phone,
                            formattedPhone = contacts.formattedPhone,
                            facebook = contacts.facebookName,
                            twitter = contacts.twitter,
                            instagram = contacts.instagram,
                        )
                    }
                },
                categories = it.categories.map { category ->
                    category.name
                },
                related = emptyList(),
            )
        }
    }
}