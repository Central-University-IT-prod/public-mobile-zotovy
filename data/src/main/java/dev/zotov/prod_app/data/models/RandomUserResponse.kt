package dev.zotov.prod_app.data.models

data class RandomUserResponse(
    val results: List<RandomUserResponseResult>
)

data class RandomUserResponseResult(
    val gender: String,
    val name: RandomUserResponseName,
    val email: String,
    val login: RandomUserResponseLogin,
    val phone: String,
    val picture: RandomUserResponsePicture,
)

data class RandomUserResponseName(
    val first: String,
    val last: String,
)

data class RandomUserResponseLogin(
    val username: String,
)

data class RandomUserResponsePicture(
    val large: String
)