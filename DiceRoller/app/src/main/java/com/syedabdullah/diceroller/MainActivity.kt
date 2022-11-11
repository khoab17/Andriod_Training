package com.syedabdullah.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button =findViewById(R.id.buttonRoll)

        // this event handler will update the dice value on the textView
        rollButton.setOnClickListener{
            val dice =rollDice()
            checkResult(dice)
        }
    }

    //update the dice with value 1 to 6 and show a toast that the dice is rolled!
    private fun rollDice() : Int{
        val dice=Dice(6)
        val diceRoll=dice.roll()
        val diceTextView:TextView=findViewById(R.id.diceTextView)
        diceTextView.text=diceRoll.toString()
        Toast.makeText(this,"Dice Rolled!", Toast.LENGTH_SHORT).show()
        return diceRoll
    }

    //checking result if the user get the lucky number
    private fun checkResult(dice:Int){
        val resultView:TextView=findViewById(R.id.resultTextView)
        var lucky=luckyNumber(6)
        if(dice==lucky){
            resultView.text="Congratulation! You won."
        }
        else{
            resultView.text="Sorry! lucky number was $lucky"
        }
    }

    //return a lucky number
    private fun luckyNumber(numSides:Int):Int{
        return (1..6).random()
    }
}

class Dice(private val numSides:Int){
    //return a random number from 1 to numSides
    fun roll():Int{
        return (1..numSides).random()
    }
}