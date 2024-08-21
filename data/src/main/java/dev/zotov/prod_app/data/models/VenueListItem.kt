package dev.zotov.prod_app.data.models

import dev.zotov.prod_app.data.interfaces.FeedItem

data class VenueListItem(
    override val id: String,
    val photoUrl: String?,
    val name: String,
    val category: String?,
    val location: String,
    val distance: String,
    val photos: List<String>,
): FeedItem
