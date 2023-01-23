package com.syedabdullah.newsstream.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.snackbar.Snackbar
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.databinding.ActivityMainBinding
import com.syedabdullah.newsstream.model.NewsArticle
import com.syedabdullah.newsstream.network.InternetConnection
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.BUSINESS
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.ENTERTAINMENT
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.GENERAL
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.SPORTS
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.TOP_NEWS
import com.syedabdullah.newsstream.viewmodel.NewsViewModel
import com.syedabdullah.newsstream.workers.ApiWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private var reserveData:List<NewsArticle> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val work = PeriodicWorkRequestBuilder<ApiWorker>(2, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(this).enqueue(work)

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

        //Checking Internet Connection when the app load the home page.
        if(InternetConnection.isOnline(this)){
            //viewModel.fetchApiNewsByCategory(TOP_NEWS)
            fetchAllNewsApi()
        }
        else{
            Snackbar.make(binding.bottomNav,"No Internet Connection !!!", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

/*

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu_bar, menu)
        val searchItem = menu.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Snackbar.make(binding.bottomNav,"$query submit!",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Snackbar.make(binding.bottomNav,"$newText change!",Snackbar.LENGTH_SHORT).show()
                    if (!newText.isNullOrEmpty()) {
                        if(newText.length > 1){
                            reserveData = viewModel.searchNews(newText)
                        }
//                        else{
//                            viewModel.updateNewsArticles(reserveData)
//                        }
                    }
                    return false
                }
            })
        }
        return true
    }
*/

    private fun fetchAllNewsApi(){
        viewModel.fetchApiNewsByCategory(GENERAL)
        viewModel.fetchApiNewsByCategory(BUSINESS)
        viewModel.fetchApiNewsByCategory(ENTERTAINMENT)
        viewModel.fetchApiNewsByCategory(SPORTS)
        viewModel.fetchApiNewsByCategory(TOP_NEWS)
    }

}