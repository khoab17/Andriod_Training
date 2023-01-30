package com.syedabdullah.sharedpreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syedabdullah.sharedpreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val prefName = "Preference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = this.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("email",null)
        val userPassword = sharedPreferences.getString("password",null)

        if(userEmail != null) binding.teUserEmail.setText(userEmail)
        if(userPassword != null) binding.teUserPassword.setText(userPassword)

        binding.buttonLogin.setOnClickListener {
            val editPref =sharedPreferences.edit()
            if (binding.cbRememberMe.isChecked){
                editPref.putString("email",binding.teUserEmail.text.toString())
                editPref.putString("password",binding.teUserPassword.text.toString())
                editPref.apply()
            }
            else{
                editPref.putString("email","")
                editPref.putString("password","")
                editPref.apply()
            }
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
     /*   sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        binding.buttonSave.setOnClickListener {
            val editMode = sharedPreferences.edit()
            editMode.putString("data", binding.teTitle.text.toString())
            editMode.apply()
            Toast.makeText(this, "Data stored.", Toast.LENGTH_SHORT).show()
        }
        binding.buttonLoad.setOnClickListener {
            binding.tvTitle.text = sharedPreferences.getString("data", "")
        }*/
    }
}