package com.ezatpanah.servicesyoutube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ezatpanah.servicesyoutube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnStart.setOnClickListener {
                startService(Intent(this@MainActivity,MyService::class.java))
            }

            btnStop.setOnClickListener {
                stopService(Intent(this@MainActivity,MyService::class.java))
            }
        }
        Intent(this, HelloService::class.java).also { intent ->
            val demo =startService(intent)
            Toast.makeText(this, "$demo", Toast.LENGTH_SHORT).show()
        }


    }
}