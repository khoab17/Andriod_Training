package com.syedabdullah.roomdbtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.syedabdullah.roomdbtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController=navHostFragment.navController
        setupActionBarWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}