package com.syedabdullah.newsstream.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

class ConnectivityReceiver : BroadcastReceiver() {
    companion object{
        private var connection = true
    }
    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected) {
            Toast.makeText(context, "No internet available", Toast.LENGTH_LONG).show()
            connection = false
        }
        else{
            if(!connection){
                Toast.makeText(context, "Network restored", Toast.LENGTH_LONG).show()
                connection = true
            }
        }
    }
}