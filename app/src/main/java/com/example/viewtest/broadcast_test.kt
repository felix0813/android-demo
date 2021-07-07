package com.example.viewtest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class broadcast_test : BaseActivity() {
    private lateinit var Receiver1:myBroadcastReceiver
    private lateinit var Receiver2:myBroadcastReceiver2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_test)
        load_toolbar()
        val intentFilter=IntentFilter()
        intentFilter.addAction("first_broadcast")
        Receiver1=myBroadcastReceiver()
        registerReceiver(Receiver1,intentFilter)
        Receiver2=myBroadcastReceiver2()
        registerReceiver(Receiver2,intentFilter)
        val button1=findViewById<Button>(R.id.send_broadcast)
        button1.setOnClickListener {
            val intent=Intent("first_broadcast")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(Receiver1)
        unregisterReceiver(Receiver2)
    }
    inner class myBroadcastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Receiver1 Receive a broadcast",Toast.LENGTH_SHORT).show()
        }
    }
    inner class myBroadcastReceiver2: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"Receiver2 Receive a broadcast",Toast.LENGTH_SHORT).show()
        }
    }
}