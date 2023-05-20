package com.kotlinegitim.r

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProductDetail : AppCompatActivity() {

    lateinit var idTxt : TextView
    lateinit var priceTxt: TextView
    lateinit var descriptionTxt : TextView
    lateinit var discountTxt : TextView
    lateinit var ratingTxt : TextView
    lateinit var stockTxt : TextView
    lateinit var brandTxt : TextView
    lateinit var categoryTxt : TextView
    lateinit var image : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        idTxt = findViewById(R.id.id_product)
        priceTxt = findViewById(R.id.price)
        descriptionTxt = findViewById(R.id.description)
        discountTxt = findViewById(R.id.discountPercantage)
        ratingTxt = findViewById(R.id.rating)
        stockTxt = findViewById(R.id.stock)
        brandTxt = findViewById(R.id.brand)
        categoryTxt = findViewById(R.id.category)
        image = findViewById(R.id.proImage)



        val id = intent.getLongExtra("id",0)
        val description = intent.getStringExtra("description")
        val price = intent.getLongExtra("price",0)
        val discount = intent.getLongExtra("discount",0)
        val rating = intent.getDoubleExtra("rating",0.0)
        val stock = intent.getLongExtra("stock",0)
        val brand = intent.getStringExtra("brand")
        val category = intent.getStringExtra("category")
        val images = intent.getStringExtra("images")

        idTxt.text = "ID:"+id.toString()
        descriptionTxt.text ="DESCRIPTION:"+ description
        priceTxt.text = "PRICE:"+ price.toString()
        discountTxt.text = "DISCOUNT:"+discount.toString()
        ratingTxt.text ="RATING:"+rating.toString()
        stockTxt.text = "STOCK:"+stock.toString()
        brandTxt.text ="BRAND:"+brand
        categoryTxt.text ="CATEGORY:"+category



        Glide.with(this).load(images).into(image)







    }
}