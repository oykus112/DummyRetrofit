package com.kotlinegitim.r.services

import com.kotlinegitim.r.Product
import com.kotlinegitim.r.models.User
import com.kotlinegitim.r.models.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DummyService {

    @POST("/auth/login")
    fun login( @Body myUser: User): Call<UserData>

    @GET("products/{id}")
    fun singleProduct( @Path("id") id: Int ) : Call<Product>
}