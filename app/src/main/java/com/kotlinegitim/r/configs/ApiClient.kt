package com.kotlinegitim.r.configs

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private val Base_URL = "https://dummyjson.com/"
    private var retrofit : Retrofit? = null

    fun getClient(): Retrofit {

        val client = OkHttpClient.Builder()
            .readTimeout(15000, TimeUnit.SECONDS)
            .build()
        if (retrofit == null){


            retrofit = Retrofit
                .Builder()
                .baseUrl(Base_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit as Retrofit

    }
}