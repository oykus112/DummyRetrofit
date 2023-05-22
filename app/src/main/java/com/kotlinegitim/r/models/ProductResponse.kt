package com.kotlinegitim.r.models


    data class ProductResponse(

        val id : Long,
        val products : List<ProductRes>,
        val total: Long,
        val discountedTotal: Long,
        val userID: Long,
        val totalProducts: Long,
        val totalQuantity: Long

    )


    data class ProductRes (

        val id: Long,
        val title: String,
        val price: Long,
        val quantity: Long,
        val total: Long,
        val discountPercentage: Double,
        val discountedPrice: Long
    )
