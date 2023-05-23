package com.kotlinegitim.r

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.ArrayAdapter
import android.widget.ListView
import com.kotlinegitim.r.configs.ApiClient
import com.kotlinegitim.r.customadaptors.BasketCustomAdaptor
import com.kotlinegitim.r.models.Cart
import com.kotlinegitim.r.models.Products
import com.kotlinegitim.r.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Basket : AppCompatActivity() {

    lateinit var dummyService: DummyService

    lateinit var  listBasket : ListView

    //lateinit var list: MutableList<Products>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        dummyService = ApiClient.getClient().create(DummyService::class.java)

        listBasket = findViewById(R.id.added)




    }

    override fun onStart() {

        var listproduct = mutableListOf<Products>()


        var id = intent.getLongExtra("id_no",0)
        var quantity = intent.getLongExtra("quantity_no",0)
        var obj = Products(id,quantity)

        if (id >0){

            listproduct.add(obj)
        }

        val adaptor = BasketCustomAdaptor(this@Basket,R.layout.basket_custom_layout,listproduct)


        dummyService.getProduct(1).enqueue(object : Callback<Cart> {
            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                val data = response.body()

                if (data != null){

                    for ( item in data.products){

                        listproduct.add(item)


                        listBasket.adapter = adaptor
                        adaptor.notifyDataSetChanged()
                    }


                }
            }

            override fun onFailure(call: Call<Cart>, t: Throwable) {
                println("Not yet implemented")
            }

        })

        super.onStart()
    }


}