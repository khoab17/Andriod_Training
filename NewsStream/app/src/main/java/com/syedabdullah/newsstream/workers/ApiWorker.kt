package com.syedabdullah.newsstream.workers

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewModelScope
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.dao.NewsDatabase
import com.syedabdullah.newsstream.network.NewsApi
import com.syedabdullah.newsstream.repository.NewsRepository
import com.syedabdullah.newsstream.ui.MainActivity
import com.syedabdullah.newsstream.viewmodel.Constant
import kotlinx.coroutines.*

class ApiWorker(private val context: Context, private val workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    private  var repository:NewsRepository
    init {
        val newsDao = NewsDatabase.getDatabase(Application()).NewsDao()
        repository = NewsRepository(newsDao)
    }
    companion object{
        const val CHANNEL_ID = "new_stream_data"
        const val NOTIFICATION_ID = 1
    }

    override suspend fun doWork(): Result {
        val connectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        val isConnected = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        if (isConnected){
        withContext(Dispatchers.IO){
               fetchAllNewsApi()
            }
            showNotification(true)
            return Result.success()
        }
        else {
            showNotification(false)
            return Result.retry()
        }
    }


    private fun showNotification(boolean: Boolean){
        val intent = Intent (applicationContext,MainActivity::class.java)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, 0
        )


        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        if(boolean) {
                builder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Data updated")
                .setContentText("Fetched data from the internet.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)
        }
        else{
            builder
            .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Updating data failed")
                .setContentText("No internet connection")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName = "Channel Name"
            val channelDescription = "Channel Description"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, channelName, channelImportance).apply {
                description =channelDescription
            }

            val notificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            )as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(applicationContext)){
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    //Apis calls
    private fun fetchAllNewsApi(){
        fetchApiNewsByCategory(Constant.GENERAL)
        fetchApiNewsByCategory(Constant.BUSINESS)
        fetchApiNewsByCategory(Constant.ENTERTAINMENT)
        fetchApiNewsByCategory(Constant.SPORTS)
        fetchApiNewsByCategory(Constant.TOP_NEWS)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchApiNewsByCategory(category:String){
        GlobalScope.launch {
            try {
                if(category == Constant.TOP_NEWS)
                {
                    val articles = NewsApi.retrofitService.getTopHeadlineNews().articles
                    val newsArticle = Constant.bindAllArticleToNewsArticles(articles, Constant.TOP_NEWS)
                    repository.addAllNewsArticles(newsArticle)
                }
                else{
                    val article = NewsApi.retrofitService.getNewsByCategory(category).articles
                    val newsArticle =Constant.bindAllArticleToNewsArticles(article,category)
                    repository.addAllNewsArticles(newsArticle)

                }
            }
            catch (e:Exception) {
                Log.d("info", "fetchApiNewsByCategory: $e")
            }
        }
    }
}