package com.syedabdullah.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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
            imageView(dice)
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

    //set dice value to Image view
    private fun imageView(dice:Int){
        val diceImageView:ImageView=findViewById(R.id.diceImageView)
        when(dice){
            1->diceImageView.setImageResource(R.drawable.dice_1)
            2->diceImageView.setImageResource(R.drawable.dice_2)
            3->diceImageView.setImageResource(R.drawable.dice_3)
            4->diceImageView.setImageResource(R.drawable.dice_4)
            5->diceImageView.setImageResource(R.drawable.dice_5)
            else->diceImageView.setImageResource(R.drawable.dice_6)
        }
    }


    //checking result if the user get the lucky Dice
    private fun checkResult(dice:Int){
        val resultView:TextView=findViewById(R.id.resultTextView)
        val luckyNum=luckyNumber(6)
        if(dice==luckyNum){
            "Congratulation! You won.".also { resultView.text = it }
        }
        else{
            "Sorry! lucky dice was $luckyNum".also { resultView.text = it }
        }
    }

    //return a lucky Dice
    private fun luckyNumber(numSides:Int):Int{
        return (1..numSides).random()
    }
}

// Define a Class for Dice
class Dice(private val numSides:Int){
    //return a random number from 1 to numSides
    fun roll():Int{
        return (1..numSides).random()
    }
}