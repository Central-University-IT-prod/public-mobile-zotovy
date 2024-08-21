package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.VenuePhoto

interface VenuePhotoMapper {

    fun map(photo: VenuePhoto): String
}