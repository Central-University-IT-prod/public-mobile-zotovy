package dev.zotov.prod_app.data.models

import java.time.LocalDate

interface Task {
    val id: String
    val name: String
    val date: LocalDate
}

data class CustomTask(
    override val id: String,
    override val name: String,
    override val date: LocalDate,
    val note: String,
): Task

data class VenueTask(
    override val id: String,
    override val name: String,
    override val date: LocalDate,
    val venue: Venue,
): Task