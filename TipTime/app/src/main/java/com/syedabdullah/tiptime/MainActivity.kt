package com.syedabdullah.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.syedabdullah.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

const val TAG: String = "Debug"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val unused = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.incrementPerson.setOnClickListener {
            peopleCount("increment")
        }
        binding.decrementPerson.setOnClickListener {
            peopleCount("decrement")
        }

        binding.manualPercent.setOnClickListener {
            manualEditTip()
        }
    }

    //function that calculate tip and cost per person
    private fun calculateTip() {
        val costOfService: Double? = (binding.costOfService.text.toString()).toDoubleOrNull()
        if (costOfService == null || costOfService == 0.0) {
            binding.tipResult.text = getString(R.string.empty_cost_of_service)
            return
        }
        val tipPercentage: Double = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            R.id.option_ten_percent -> 0.10
            R.id.manual_percent -> getManualTip()
            else -> 0.0
        }
        val peoples: Int = (binding.numberOfPersonCount.text.toString()).toInt()
        var tip: Double = costOfService.times(tipPercentage)
        var perPersonCost = (costOfService.times(tipPercentage) + costOfService).div(peoples)

        if (binding.roundUpTip.isChecked) {
            tip = kotlin.math.ceil(tip)
            perPersonCost = kotlin.math.ceil(perPersonCost)
        }

        val tipResult: TextView = binding.tipResult
        tipResult.text = getString(
            R.string.tip_amount,
            currencyFormat(costOfService + tip),
            currencyFormat(perPersonCost),
            currencyFormat(tip)
        )

        Log.d(TAG, "tip: $tip and person bill $perPersonCost")
        Log.d(TAG, binding.tipOptions.checkedRadioButtonId.toString())
        Log.d(TAG, NumberFormat.getCurrencyInstance().format(tip))
        Log.d(TAG, tip.toString())
    }

    //manualTip view func
    private fun manualEditTip() {
        val manualTipEdit: EditText = binding.insertManualTip
        manualTipEdit.visibility = View.VISIBLE
    }

    //manual tip null check
    private fun getManualTip(): Double {
        val res = binding.insertManualTip.text.toString().toDoubleOrNull()
        return res?.div(100) ?: 0.0
    }

    //increment or decrement of number of peoples
    private fun peopleCount(flag: String) {
        val personCountText = binding.numberOfPersonCount
        val people = personCountText.text.toString().toInt()
        when (flag) {
            "increment" -> {
                (people + 1).toString().also { personCountText.text = it }
            }
            else -> {
                if (people > 2)
                    personCountText.text = (people - 1).toString()
                else
                    personCountText.text = getString(R.string.one)
            }
        }
    }

    //return value in Currency Format.
    private fun currencyFormat(num: Double): String {
        return NumberFormat.getCurrencyInstance().format(num)
    }
}