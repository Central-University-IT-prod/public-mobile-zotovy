package dev.zotov.prod_app.data.mappers

import dev.zotov.prod_app.data.interfaces.RandomUserToUserMapper
import dev.zotov.prod_app.data.models.RandomUserResponse
import dev.zotov.prod_app.data.models.User

object RandomUserToUserMapperImpl : RandomUserToUserMapper {
    override fun map(raw: RandomUserResponse): User {
        return raw.results.first().let {
            User(
                gender = it.gender,
                name = it.name.let { name ->
                    "${name.first} ${name.last}"
                },
                email = it.email,
                password = "",
                phone = it.phone,
                username = it.login.username,
                profilePicture = it.picture.large,
            )
        }
    }
}