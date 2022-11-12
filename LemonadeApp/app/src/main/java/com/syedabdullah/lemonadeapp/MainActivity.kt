package com.syedabdullah.lemonadeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var state:Int=0
    private val lemon_state:Int=(3..6).random();
    private var lemon_state2:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val treeImageView:ImageView=findViewById(R.id.treeImageView)
        val treeTextView:TextView=findViewById(R.id.treeTextView)
        treeImageView.setOnClickListener{
            when (state){
                0->{
                    treeTextView.text=getString(R.string.lemon_message)
                    treeImageView.setImageResource(R.drawable.lemon)
                    state++
                }
                1->{
                    lemon_state2++
                    if(lemon_state==lemon_state2){
                        treeTextView.text=getString(R.string.juice_message)
                        treeImageView.setImageResource(R.drawable.juice)
                        state++
                        lemon_state2=0
                    }
                }
                else->{
                    treeTextView.text=getString(R.string.welcome_message)
                    treeImageView.setImageResource(R.drawable.lemon_tree)
                    state=0
                }
            }
        }
    }

    private fun returnRandomValue(start:Int ,end:Int):Int{
        return (start..end).random()
    }
}