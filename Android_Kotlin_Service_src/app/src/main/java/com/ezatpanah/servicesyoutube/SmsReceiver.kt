package com.ezatpanah.servicesyoutube

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        try {
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<*>
                for (i in pdus.indices) {
                    val smsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                    val phoneNumber = smsMessage.displayOriginatingAddress
                    val message = smsMessage.messageBody
                    Toast.makeText(context, "From: $phoneNumber\nMessage: $message", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}