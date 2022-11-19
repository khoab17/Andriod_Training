package com.syedabdullah.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.syedabdullah.tiptime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculateTip:Button=binding.calculateButton
        calculateTip.setOnClickListener{
            calculateTip()
        }

        val incrementPeople=binding.incrementPerson
        incrementPeople.setOnClickListener {
            peopleCount("increment")
        }
        val decrementPeople=binding.decrementPerson
        decrementPeople.setOnClickListener {
            peopleCount("decrement")
        }
    }

    //function that calculate tip
    private fun calculateTip(){
        val costOfService: Double = (binding.costOfService.text.toString()).toDoubleOrNull() ?: return
        val tipPercentage:Double=when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent->0.2
            R.id.option_eighteen_percent->0.18
            R.id.option_fifteen_percent->0.15
            R.id.option_ten_percent->0.10
            else->0.0
        }
        val peoples:Int=(binding.numberOfPersonCount.text.toString()).toInt()
        var tip:Double = costOfService.times(tipPercentage)

        var result=(costOfService.times(tipPercentage)+costOfService).div(peoples)

        if(binding.roundUpTip.isChecked) {
            result=kotlin.math.ceil(result)
        }

        val tipResult:TextView=binding.tipResult
        result.toString().also { tipResult.text=it }
        Log.d("Bug",result.toString())
        Log.d("Id",binding.tipOptions.checkedRadioButtonId.toString())
    }

    //increment or decrement people count
    private fun peopleCount(flag:String){
        val personCountText=binding.numberOfPersonCount
        val people=personCountText.text.toString().toInt()
        when(flag){
            "increment"->{
                (people+1).toString().also { personCountText.text = it }
            }
            else->{
                if(people>2)
                    personCountText.text=(people-1).toString()
                else
                    personCountText.text=getString(R.string.one)
            }
        }
    }
}