package dev.zotov.prod_app.data.api

import dev.zotov.prod_app.data.models.NearByVenuesResponse
import dev.zotov.prod_app.data.models.RelatedVenueResponse
import dev.zotov.prod_app.data.models.VenueDetailResponse
import dev.zotov.prod_app.data.models.VenuePhoto
import dev.zotov.prod_app.data.models.VenuesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface VenuesApi {
    @GET("/v2/search/recommendations?v=20240317&oauth_token=secret")
    suspend fun getList(
        @Query("ll") latLon: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Header("Accept-Language") language: String = "ru"
    ): Response<VenuesListResponse>

    @GET("/v2/venues/{id}/?v=20240317&oauth_token=secret")
    suspend fun getById(
        @Path("id") id: String,
        @Header("Accept-Language") language: String = "ru"
    ): Response<VenueDetailResponse>

    @GET("/v2/venues/{id}/related?v=20240317&oauth_token=secret")
    suspend fun getRelated(
        @Path("id") id: String,
        @Header("Accept-Language") language: String = "ru"
    ): Response<RelatedVenueResponse>

    @GET("/v3/places/{id}/photos?sort=popular")
    suspend fun getPhotos(
        @Path("id") id: String,
        @Header("Accept-Language") language: String = "ru",
        @Header("Authorization") token: String = "secret",
    ): Response<List<VenuePhoto>>

    @GET("v2/venues/search?v=20241001&radius=1000&oauth_token=secret&categoryId=secret,secret,secret&limit=5")
    suspend fun getNearBy(
        @Query("ll") latLon: String,
        @Header("Accept-Language") language: String = "ru",
    ): Response<NearByVenuesResponse>
}