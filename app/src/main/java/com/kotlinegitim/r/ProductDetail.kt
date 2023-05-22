package com.kotlinegitim.r

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kotlinegitim.r.configs.ApiClient
import com.kotlinegitim.r.models.Cart
import com.kotlinegitim.r.models.ProductResponse
import com.kotlinegitim.r.models.Products
import com.kotlinegitim.r.services.DummyService
import org.json.JSONObject
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


        dummyService = ApiClient.getClient().create(DummyService::class.java)



        add.setOnClickListener {


            //val list= mutableListOf<Products>()

            val products = mutableListOf<Products>()
            //val carts =  mutableListOf<Cart>()

            val product = Products(1,1)
            val product2 = Products(50,2)
            products.add(product)
            products.add(product2)


            //list.add(Products(1,1))
            val cart = Cart(1,products)


            dummyService.Add(cart).enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {


                    val data = response.body()

                    println("code hata:")

                    var error = response.errorBody().toString()
                    println (error)


                    //var error = Gson().fromJson(response.errorBody()?.string(),Cart::class.java)


                    if (data != null){

                        Log.d("detay", data.id.toString())
                    }

                    println("sepete ekle")
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    println("Not yet implemented")
                }


            })
        }


       /* add.setOnClickListener{

            dummyService.getProduct(1).enqueue(object : Callback<Cart>{
                override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                   val data = response.body()

                    if (data != null){

                        println(data.products)
                    }
                }

                override fun onFailure(call: Call<Cart>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }*/







    }
}