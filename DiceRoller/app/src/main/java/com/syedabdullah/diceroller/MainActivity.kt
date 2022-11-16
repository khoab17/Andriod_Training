package com.syedabdullah.diceroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var score=0
    private var state=false
    private var mineList= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button =findViewById(R.id.buttonRoll)

        // this event handler will update the dice value on the textView
        rollButton.setOnClickListener{
            if(!state){
                mineList=minesShow()
                state=true
            }
            val dice =rollDice()
            imageView(dice)
            updateScore(dice)
            checkResult()
        }
    }

    //check result
    @SuppressLint("SetTextI18n")
    private fun checkResult(){
        val dice=Dice(6)
        val result:TextView=findViewById(R.id.player1ScoretextView)
        if(score>50){
            result.text="Congrats! You won \$${dice.getRewards(score)}."
        }
        if(mineList.contains(score)){
            result.text="$score . You only won \$${dice.getRewards(score)} \ntry again."
            score=0
            mineList= mutableListOf()
            state=false
        }
    }

    //show mines steps
    private fun minesShow():MutableList<Int>{
        val dice=Dice(6)
        val mines =dice.getMines(5)
        val guessMessage:TextView=findViewById(R.id.guessMessageTextView)
        "Numbers That need to Avoid: \n${mines}".also { guessMessage.text = it }
        return mines
    }


    //set score in score text view
    private fun updateScore(dice:Int){
        val scoreView:TextView=findViewById(R.id.player1ScoretextView)
        score+=dice
        "Current Score: $score".also { scoreView.text = it }
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
}

// Define a Class for Dice
class Dice(private val numSides:Int){
    //return a random number from 1 to numSides
    fun roll():Int{
        return (1..numSides).random()
    }
    fun getMines(level:Int):MutableList<Int>{
       val mines= mutableListOf<Int>()
       for(i in 0 until level)
       {
           mines.add(i*10+(1..5).random())
           mines.add(i*10+(6..9).random())
       }
        return mines
    }
    fun getRewards(score:Int):Int{
        return when(score){
            in 1..10-> 1
            in 11..20-> 5
            in 21..30-> 10
            in 31..40-> 15
            in 41..50-> 20
            else-> 50
        }
    }
    //created this function for Unit testing purposes
    fun timeout(n:Int):Boolean{
        for (i in 1..n){
            for(i in 1..n){

            }
        }
        return true
    }

    //for testing
    fun addition(a:Int,b:Int):Int{
        return a*b
    }
    //for testing
    fun getPersonObj():Person{
        val person:Person=Person("demo",123,"86127836","demo@gmail.com")
        return person
    }
}
//for Testing purposes
class Person(val name:String, val id:Int, val phone:String, val email:String)
{
    fun getDetails():String{
        return (name+id+phone+email).toString()
    }
}
