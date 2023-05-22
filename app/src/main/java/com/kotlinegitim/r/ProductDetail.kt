package com.kotlinegitim.r

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kotlinegitim.r.configs.ApiClient
import com.kotlinegitim.r.models.Cart
import com.kotlinegitim.r.models.ProductResponse
import com.kotlinegitim.r.models.Products
import com.kotlinegitim.r.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

    lateinit var dummyService: DummyService

    lateinit var add : Button

    lateinit var cart : Cart

    var imageURL : String =""




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
        add = findViewById(R.id.basket)



        val id = intent.getLongExtra("id",0)
        val image_url = intent.getStringExtra("images")

        dummyService = ApiClient.getClient().create(DummyService::class.java)


        dummyService.Product(id.toInt()).enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                val datas = response.body()

                if (datas != null) {
                    idTxt.text = "ID:"+id.toString()
                    descriptionTxt.text ="DESCRIPTION:"+ datas.description
                    priceTxt.text = "PRICE:"+ datas.price
                    discountTxt.text = "DISCOUNT:"+datas.discountPercentage
                    ratingTxt.text ="RATING:"+datas.rating
                    stockTxt.text = "STOCK:"+datas.stock
                    brandTxt.text ="BRAND:"+datas.brand
                    categoryTxt.text ="CATEGORY:"+datas.category








                }


            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println("Not yet implemented")
            }

        })

        println("Image URL $image_url")
        Glide.with(this@ProductDetail).load(image_url).into(image)




        val products = mutableListOf<Products>()

        val product = Products(id,1)
        products.add(product)

        val cart = Cart(1,products)



        add.setOnClickListener {



            dummyService.Add(cart).enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {

                    val data = response.body()

                    if(response.isSuccessful) {
                        println("SUCCESSFUL")

                        if (data != null) {

                            Toast.makeText(this@ProductDetail,"Product is add to basket !",Toast.LENGTH_LONG).show()
                            var intent = Intent(this@ProductDetail, Basket::class.java)

                            startActivity(intent)
                        }



                    } else {
                        println(response.errorBody()?.source().toString())


                    }

                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    println("Not yet implemented")
                }


            })
        }










    }
}