package com.kotlinegitim.r.services

import com.kotlinegitim.r.DummyProducts
import com.kotlinegitim.r.Product
import com.kotlinegitim.r.models.*
import retrofit2.Call
import retrofit2.http.*

interface DummyService {

    @POST("/auth/login")
    fun login( @Body myUser: User): Call<UserData>

    @GET("products")
    fun getProducts() : Call<DummyProducts>

    @GET("products/{id}")
    fun singleProduct( @Path("id") id: Int ) : Call<Product>

    @Headers("Content-Type: application/json")
    @POST("/carts/add")

    /*@HTTP(method = "POST", path = "carts/add", hasBody = true,)*/
    fun Add(@Body data: Cart): Call<ProductResponse>

    @GET("carts/{id}")
    fun getProduct( @Path("id") id: Long) : Call<Cart>
}