package com.bjit.firebaseexample

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseInstanceIdService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("FCM", "Refreshed token: $token")

    }
}