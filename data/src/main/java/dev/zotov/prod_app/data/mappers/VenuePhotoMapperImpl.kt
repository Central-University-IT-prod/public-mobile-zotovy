package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.VenuePhotoMapper
import dev.zotov.prod_app.data.models.VenuePhoto

object VenuePhotoMapperImpl: VenuePhotoMapper {
    override fun map(photo: VenuePhoto): String {
        return "${photo.prefix}original${photo.suffix}"
    }
}