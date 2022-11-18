package com.syedabdullah.tiptime

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //increment and decrement
        val incrementButton:Button=findViewById(R.id.increment_person)
        val decrementButton:Button=findViewById(R.id.decrement_person)
        val calculateButton:Button=findViewById(R.id.calculate_button)

        incrementButton.setOnClickListener{
            val numberOfPerson:TextView=findViewById(R.id.number_of_person_count)
            numberOfPerson.text=((numberOfPerson.text.toString()).toInt()+1).toString()
        }
        decrementButton.setOnClickListener{
            val numberOfPerson:TextView=findViewById(R.id.number_of_person_count)
            val temp=(numberOfPerson.text.toString()).toInt()-1
            if(temp>0) {
                numberOfPerson.text = temp.toString()
            }else{
                numberOfPerson.text="1"
            }
        }
    }
}