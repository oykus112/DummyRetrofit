package com.kotlinegitim.r

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.kotlinegitim.r.configs.ApiClient
import com.kotlinegitim.r.models.User
import com.kotlinegitim.r.models.UserData
import com.kotlinegitim.r.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var dummyService: DummyService
    lateinit var name : EditText
    lateinit var pass : EditText
    lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.editTextTextPersonName2)
        pass = findViewById(R.id.editTextTextPersonName3)
        button = findViewById(R.id.button)


        dummyService = ApiClient.getClient().create(DummyService::class.java)

        button.setOnClickListener (btnOnClickListener)

    }
    val btnOnClickListener = View.OnClickListener {
        val username = name.text.toString()
        val pass = pass.text.toString()
        val jwtUser = User(username,pass)

        if (jwtUser.username.isNullOrEmpty() || jwtUser.password.isNullOrEmpty() ) {


            Toast.makeText(this,"username ya da email boş ",Toast.LENGTH_LONG).show()
        }
        else {
            dummyService.login(jwtUser).enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    val myUser = response.body()
                    Log.d("status", response.code().toString())

                    if (myUser != null) {


                        val intent = Intent(this@MainActivity, ProductsLists::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.e("login", t.toString())
                    Toast.makeText(this@MainActivity, "Internet or Server Fail", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }
}