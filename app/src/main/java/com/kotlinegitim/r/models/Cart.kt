package com.kotlinegitim.r.models

import com.kotlinegitim.r.Product

data class Cart(
    val userID: Long,
    val products: List<Products>
)

data class Products (
    val id: Long,
    val quantity: Long
)

