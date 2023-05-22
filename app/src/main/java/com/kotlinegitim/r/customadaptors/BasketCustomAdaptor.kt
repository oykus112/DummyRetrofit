package com.kotlinegitim.r.customadaptors

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.kotlinegitim.r.ProductDetail
import com.kotlinegitim.r.R
import com.kotlinegitim.r.models.Products

class BasketCustomAdaptor(private val context: Activity, private val resource: Int, private val objects: MutableList<Products>) :
    ArrayAdapter<Products>(context, resource, objects) {

    lateinit var textID : TextView
    lateinit var textQuantity : TextView


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var root = context.layoutInflater.inflate(resource,null,true)

        textID = root.findViewById<TextView>(R.id.proid)
        textQuantity= root.findViewById<TextView>(R.id.quantity)


        var products = objects.get(position)

        textID.text = products.id.toString()
        textQuantity.text = products.quantity.toString()


        return root
    }
}