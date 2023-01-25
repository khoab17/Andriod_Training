package com.syedabdullah.newsstream.ui

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.*
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.broadcast.ConnectivityReceiver
import com.syedabdullah.newsstream.databinding.ActivityMainBinding
import com.syedabdullah.newsstream.network.InternetConnection
import com.syedabdullah.newsstream.util.ClassConverter.Companion.TOP_NEWS
import com.syedabdullah.newsstream.viewmodel.NewsViewModel
import com.syedabdullah.newsstream.workers.ApiWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var mConnectivityReceiver:ConnectivityReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager()
        mConnectivityReceiver = ConnectivityReceiver()
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(mConnectivityReceiver, filter)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val bottomNav = binding.bottomNav
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.bookmark -> {
                    navController.navigate(R.id.bookmarkFragment)
                    true
                }
                else -> false
            }
        }
        if(InternetConnection.isOnline(this)) {
            viewModel.fetchAllNewsApi()
        }
        viewModel.getNewsByCategory(TOP_NEWS)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun workManager(){
        val constraint = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()
        val myRequest= PeriodicWorkRequest.Builder(
            ApiWorker::class.java,
            300,
            TimeUnit.MINUTES
        ).setConstraints(constraint).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "fetch_api_work",
            ExistingPeriodicWorkPolicy.KEEP,
            myRequest
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mConnectivityReceiver)
    }

}