package com.syedabdullah.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
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

        val manualRadioButton:RadioButton=binding.manualPercent
        manualRadioButton.setOnClickListener {
            manualEditTip()
        }
    }

    //manual tip null check
    private fun manualCheck():Double{
        val res=binding.insertManualTip.text.toString().toIntOrNull()
        return res?.toDouble()?.div(100) ?: 0.0

    }

    //function that calculate tip
    private fun calculateTip(){
        val costOfService: Double = (binding.costOfService.text.toString()).toDoubleOrNull() ?: return
        val tipPercentage:Double=when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent->0.2
            R.id.option_eighteen_percent->0.18
            R.id.option_fifteen_percent->0.15
            R.id.option_ten_percent->0.10
            R.id.manual_percent->manualCheck()
            else->0.0
        }
        val peoples:Int=(binding.numberOfPersonCount.text.toString()).toInt()
        var tip:Double = costOfService.times(tipPercentage)

        var result=(costOfService.times(tipPercentage)+costOfService).div(peoples)

        if(binding.roundUpTip.isChecked) {
            tip=kotlin.math.ceil(tip)
            result=kotlin.math.ceil(result)
        }

        val tipResult:TextView=binding.tipResult
        "Total bill: $result per Person\nAnd tip is only: $tip".also { tipResult.text=it }

        Log.d("Check", "tip: $tip and person bill $result")
        Log.d("Check",binding.tipOptions.checkedRadioButtonId.toString())
    }

    //manualTip view func
    private fun manualEditTip(){
        val manualTipEdit:EditText=binding.insertManualTip
        manualTipEdit.visibility=View.VISIBLE
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