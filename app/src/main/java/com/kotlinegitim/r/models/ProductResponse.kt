package com.kotlinegitim.r.models

import com.kotlinegitim.r.Product


data class ProductResponse(

    val id: Long,
    val products: List<ProductRes>,
    val total: Long,
    val discountedTotal: Long,
    val userId: Long,
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
