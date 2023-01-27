package com.ezatpanah.servicesyoutube


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class ConnectivityReceiver : BroadcastReceiver() {
    private var connection = true

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork
        val networkCapabilities = cm.getNetworkCapabilities(network)
        val isConnected: Boolean = networkCapabilities != null && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET)

        if (!isConnected) {
            Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show()
            connection = false
        }
        else{
            if(!connection){
                Toast.makeText(context, "Connection restored", Toast.LENGTH_LONG).show()
                connection = true
            }
        }
    }
}