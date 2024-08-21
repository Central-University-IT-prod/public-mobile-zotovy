package dev.zotov.prod_app.data.repositories

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2Mode
import dev.zotov.prod_app.data.api.UserApi
import dev.zotov.prod_app.data.interfaces.RandomUserToUserMapper
import dev.zotov.prod_app.data.interfaces.UsersRepository
import dev.zotov.prod_app.data.models.User


class UsersRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val userApi: UserApi,
    private val randomUserToUserMapper: RandomUserToUserMapper,
) : UsersRepository {
    override fun currentUser(): User? {
        try {
            val username = sharedPreferences.getString("current-user", "") ?: return null
            return getByUsername(username)
        } catch (e: Throwable) {
            Log.e("currentUser", "Failed to get current user", e)
            return null
        }
    }

    override fun setCurrentUser(username: String?) {
        try {
            with(sharedPreferences.edit()) {
                if (username == null) remove("current-user")
                else putString("current-user", username)
                apply()
            }
        } catch (e: Throwable) {
            Log.e("setCurrentUser", "Failed to set current user $username", e)
        }
    }

    override fun getByUsername(username: String): User? {
        try {
            val raw = sharedPreferences.getString("user-${username}", "") ?: return null
            Log.d("users", sharedPreferences.all.toString())
            return Gson().fromJson(raw, User::class.java)
        } catch (e: Throwable) {
            Log.e("getByUsername", "Failed to get by username $username", e)
            return null
        }
    }

    override fun save(user: User) {
        try {
            val raw = Gson().toJson(user)
            with(sharedPreferences.edit()) {
                putString("user-${user.username}", raw)
                apply()
            }
        } catch (e: Throwable) {
            Log.e("getByUsername", "Failed to save user $user", e)
        }
    }

    override fun checkUsernameUnique(username: String): Boolean {
        return try {
            sharedPreferences.contains("user-$username")
        } catch (e: Throwable) {
            Log.e("checkUsernameUnique", "Failed to check $username", e)
            false
        }
    }

    override suspend fun getRandomUser(): User? {
        try {
            val response = userApi.getRandomUser()
            val body = (if (response.isSuccessful) response.body() else null) ?: return null
            return randomUserToUserMapper.map(body)
        } catch (e: Throwable) {
            Log.e("getRandomUser", "Failed to get random user", e)
            return null
        }
    }

    override fun hashPassword(password: String): String {
        return Argon2Kt().hash(
            mode = Argon2Mode.ARGON2_I,
            password = password.toByteArray(),
            salt = salt.toByteArray(),
        ).encodedOutputAsString()
    }

    override fun comparePassword(hash: String, password: String): Boolean {
        return Argon2Kt().verify(
            mode = Argon2Mode.ARGON2_I,
            encoded = hash,
            password = password.toByteArray()
        )
    }
}

private const val salt = "secret"
