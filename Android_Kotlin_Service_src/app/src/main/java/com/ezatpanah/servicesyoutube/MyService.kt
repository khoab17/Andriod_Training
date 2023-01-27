package com.ezatpanah.servicesyoutube

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast

class MyService : Service() {
    private lateinit var player : MediaPlayer

     override fun onStartCommand(init : Intent , flag : Int , startId: Int):Int{
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
         //player.isLooping = false
         if(player.isPlaying){
             Toast.makeText(this, "Music is already playing.", Toast.LENGTH_SHORT).show()
         }
         else {
             player.start()
         }
        return  START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}