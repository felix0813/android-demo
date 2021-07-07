package com.example.viewtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat

open class ForegroundService:BaseService() {
    override fun onCreate() {
        super.onCreate()
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("foregroundService","前台服务通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val notification= NotificationCompat.Builder(this,"foregroundService").
        setSmallIcon(R.drawable.small_icon).setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon)).
        setContentTitle("${javaClass.simpleName} in Felix's test").
        setContentText("This the is notification of foreground service of felix's test").
        setContentIntent(PendingIntent.getActivity(this,0, Intent(this,MainActivity::class.java),0)).
        setAutoCancel(true).
        build()
        startForeground(2,notification)
    }
}