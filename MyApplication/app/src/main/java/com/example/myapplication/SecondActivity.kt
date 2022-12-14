package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName=intent.getStringExtra("userName")
        val email=intent.getStringExtra("email")
        val address=intent.getStringExtra("address")

        val userNameTv=findViewById<TextView>(R.id.userNameDataView)
        val emailTv=findViewById<TextView>(R.id.emailDataView)
        val addressTv=findViewById<TextView>(R.id.addressDataView)

        userNameTv.text= "User Name: $userName"
        emailTv.text= "Email: $email"
        addressTv.text = "Address: $address"



        val btn=findViewById<Button>(R.id.backButton)
        btn.setOnClickListener(){
            val mainAct= Intent(this, MainActivity::class.java)
            startActivity(mainAct)
        }

    }
}