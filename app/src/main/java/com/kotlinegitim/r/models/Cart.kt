package com.kotlinegitim.r.models

import com.kotlinegitim.r.Product

data class Cart(
    val userId: Long,
    val products: List<Products>

)

data class Products (
    val id: Long,
    val quantity: Long
)

