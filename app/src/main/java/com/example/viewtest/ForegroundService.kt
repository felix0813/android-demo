package com.example.viewtest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_MAIN
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat


open class ForegroundService:BaseService() {

    override fun onCreate() {
        super.onCreate()
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("foregroundService","前台服务通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent=Intent(ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.setClass(this,Login::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
        val notification= NotificationCompat.Builder(this,"foregroundService").
        setSmallIcon(R.drawable.small_icon).setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon)).
        setContentTitle("${javaClass.simpleName} in Felix's test").
        setContentText("This the is notification of foreground service of felix's test").
        setContentIntent(PendingIntent.getActivity(this,0, intent,0)).
        setNotificationSilent().
        setAutoCancel(true).
        build()

        startForeground(2,notification)

    }

}