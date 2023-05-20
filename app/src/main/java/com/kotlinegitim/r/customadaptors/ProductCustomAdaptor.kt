package com.kotlinegitim.r.customadaptors

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kotlinegitim.r.Product
import com.kotlinegitim.r.ProductDetail
import com.kotlinegitim.r.R

class ProductCustomAdaptor ( private val context: Activity, private val layout_int : Int, private val list:List<Product> ) : ArrayAdapter<Product>(context, layout_int,list)  {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val root = context.layoutInflater.inflate(layout_int,null,true)

        val image = root.findViewById<ImageView>(R.id.imageView)
        val productName = root.findViewById<TextView>(R.id.textView2)
        var products = list.get(position)

        productName.text = products.title

        Glide.with(context).load(products.thumbnail).into(image)

        root.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, ProductDetail::class.java)

                intent.putExtra("id",products.id)
                intent.putExtra("price",products.price)
                intent.putExtra("description",products.description)
                intent.putExtra("discount",products.discountPercentage)
                intent.putExtra("rating",products.rating)
                intent.putExtra("stock",products.stock)
                intent.putExtra("brand",products.brand)
                intent.putExtra("category",products.category)

                intent.putExtra("images",products.images.get(0))

                context.startActivity(intent)

            }
        })

        return root
    }


}