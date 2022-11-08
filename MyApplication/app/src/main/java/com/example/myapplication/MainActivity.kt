package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
   //lateinit var btn :Button

    private lateinit var nameData:EditText
    private lateinit var emailData:EditText
    private lateinit var addressData:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn=findViewById<Button>( R.id.submitButton)

        nameData=findViewById(R.id.inputName)
        emailData=findViewById(R.id.inputEmail)
        addressData=findViewById(R.id.inputAdress)

        btn.setOnClickListener(){
            val sActivity=Intent(this, SecondActivity::class.java)
                .putExtra("userName",nameData.text.toString())
                .putExtra("email",emailData.text.toString())
                .putExtra("address",addressData.text.toString())
            startActivity(sActivity)

        }

    }
}