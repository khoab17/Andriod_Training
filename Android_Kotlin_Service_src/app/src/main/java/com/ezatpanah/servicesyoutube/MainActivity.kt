package com.ezatpanah.servicesyoutube


import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ezatpanah.servicesyoutube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mConnectivityReceiver:ConnectivityReceiver
    private lateinit var smsReceiver: SmsReceiver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mConnectivityReceiver = ConnectivityReceiver()
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(mConnectivityReceiver, filter)

        smsReceiver = SmsReceiver()
        val smsFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(mConnectivityReceiver, smsFilter)


    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mConnectivityReceiver)
    }






       /* binding.apply {
            btnStart.setOnClickListener {
                startService(Intent(this@MainActivity,HelloService::class.java))
            }

            btnStop.setOnClickListener {
                stopService(Intent(this@MainActivity,HelloService::class.java))
            }
        }
        Intent(this, HelloService::class.java).also { intent ->
            val demo =startService(intent)
        }
*/


}