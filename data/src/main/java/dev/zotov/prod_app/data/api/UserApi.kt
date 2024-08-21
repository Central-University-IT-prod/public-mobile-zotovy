package dev.zotov.prod_app.data.api

import dev.zotov.prod_app.data.models.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("/api/")
    suspend fun getRandomUser(): Response<RandomUserResponse>
}