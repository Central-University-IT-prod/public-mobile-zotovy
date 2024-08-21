package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.RandomUserResponse
import dev.zotov.prod_app.data.models.User

interface RandomUserToUserMapper {

    fun map(raw: RandomUserResponse): User
}