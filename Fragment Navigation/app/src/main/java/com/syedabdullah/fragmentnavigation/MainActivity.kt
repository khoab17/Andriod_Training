package com.syedabdullah.fragmentnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.syedabdullah.fragmentnavigation.fragment.AboutFragmentDirections
import com.syedabdullah.fragmentnavigation.model.User


class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.about -> {
                   navController.navigate(R.id.aboutFragment)
                    true
                }
                else -> false
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}