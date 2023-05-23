package com.kotlinegitim.r

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.kotlinegitim.r.configs.ApiClient
import com.kotlinegitim.r.customadaptors.ProductCustomAdaptor
import com.kotlinegitim.r.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsLists : AppCompatActivity() {

    lateinit var dummyService: DummyService

    var productList = mutableListOf<Product>()
    var productList_all = mutableListOf<Product>()
    lateinit var  productList2 : List<Product>
    lateinit var newList : ListView
    lateinit var button : Button
    lateinit var proEdit : EditText
    lateinit var basketbtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_lists)



        newList = findViewById(R.id.productView)
        proEdit = findViewById(R.id.editTextTextPersonName)

        basketbtn = findViewById(R.id.basketbtn)


        dummyService = ApiClient.getClient().create(DummyService::class.java)

        val adapter = ProductCustomAdaptor(this@ProductsLists, R.layout.product_custom_layout, productList_all)

        dummyService.getProducts().enqueue(object : Callback<DummyProducts>{
            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {

                val product = response.body()


                if (product != null){

                    for (item in product.products){

                        productList_all.add(item)

                    }

                }
            }

            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                TODO("Not yet implemented")
            }



        })

        newList.adapter = adapter
        adapter.notifyDataSetChanged()



        proEdit.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                productList2 = productList_all.filter { item -> item.title.contains(proEdit.text) }
                val adapter = ProductCustomAdaptor(this@ProductsLists, R.layout.product_custom_layout, productList2)

                newList.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        basketbtn.setOnClickListener {


            var intent = Intent (this, Basket::class.java)

            startActivity(intent)
        }




    }

    override fun onStart() {

        for (id in 1..10){
            dummyService.Product(id).enqueue(object : Callback<Product> {
                override fun onResponse(
                    call: Call<Product>,
                    response: Response<Product>
                ) {
                    val datas = response.body()

                    if (datas != null) {

                        productList.add(datas)

                    }


                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    println("Not yet implemented")
                }

            })

        }

        val adapter = ProductCustomAdaptor(this,R.layout.product_custom_layout, productList)

        newList.adapter = adapter
        adapter.notifyDataSetChanged()

        super.onStart()
    }


}