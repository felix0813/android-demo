package com.example.viewtest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

open class BaseService : Service() {
    private val binder=Binder()

    override fun onCreate() {
        super.onCreate()
        Log.e("service","${javaClass.simpleName} create")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("service","${javaClass.simpleName} start")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("service","${javaClass.simpleName} destroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

}