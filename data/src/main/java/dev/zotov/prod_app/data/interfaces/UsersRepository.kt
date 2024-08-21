package dev.zotov.prod_app.data.interfaces

import dev.zotov.prod_app.data.models.User

interface UsersRepository {
    fun currentUser(): User?

    fun setCurrentUser(username: String?)

    fun getByUsername(username: String): User?

    fun save(user: User)

    fun checkUsernameUnique(username: String): Boolean

    suspend fun getRandomUser(): User?

    fun hashPassword(password: String): String

    fun comparePassword(hash: String, password: String): Boolean
}