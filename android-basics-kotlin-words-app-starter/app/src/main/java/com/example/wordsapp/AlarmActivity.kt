package com.example.wordsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText

class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val setButton:Button=findViewById(R.id.btn_set_alarm)
        setButton.setOnClickListener{
            val hour:Int=findViewById<EditText>(R.id.input_alarm_hour).text.toString().toInt()
            val minutes:Int=findViewById<EditText>(R.id.input_alarm_minutes).text.toString().toInt()
            val message:String=findViewById<EditText>(R.id.input_alarm_message).text.toString()
            createAlarm(message,hour,minutes)
        }
    }

    private fun createAlarm(message: String, hour:Int, minutes:Int){
        val intent= Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,message)
            putExtra(AlarmClock.EXTRA_HOUR,hour)
            putExtra(AlarmClock.EXTRA_MINUTES,minutes)
        }
        this.startActivity(intent)
    }
}