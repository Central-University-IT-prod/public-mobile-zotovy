package dev.zotov.prod_app.data.models

data class User(
    val gender: String,
    val name: String,
    val email: String,
    val username: String,
    val password: String, // hashed
    val phone: String,
    val profilePicture: String,
)
