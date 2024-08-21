package dev.zotov.prod_app.data.common

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    fun getClient(baseUrl: String): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}