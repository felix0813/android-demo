package com.example.viewtest

import android.app.*
import android.content.*
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.viewtest.newclass.ActivityCollector


open class BaseActivity : AppCompatActivity() {



    private lateinit var receiver:offlineReceiver
    private lateinit var the_menu: Menu
    private lateinit var manager:NotificationManager
    inner class offlineReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("下线通知")
                setMessage("您已经被强制下线，请重新登陆")
                setCancelable(false)
                setPositiveButton("OK"){_,_ ->
                    ActivityCollector.exit()
                    startActivity(Intent(context,Login::class.java))
                }
                show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val IntentFilter=IntentFilter()
        IntentFilter.addAction("offline")
        receiver=offlineReceiver()
        registerReceiver(receiver,IntentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        Log.e("felix",javaClass.simpleName)
        ActivityCollector.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.remove(this)
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        if (menu != null) {
            the_menu=menu
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item-> Toast.makeText(this,"Add", Toast.LENGTH_LONG).show()
            R.id.remove_item-> Toast.makeText(this,"Remove", Toast.LENGTH_LONG).show()
            R.id.exit-> ActivityCollector.exit()
            R.id.offline->sendBroadcast(Intent("offline"))
            R.id.notification->{
                val notification=NotificationCompat.Builder(this,"normal").
                    setSmallIcon(R.drawable.small_icon).setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large_icon)).
                    setContentTitle(javaClass.simpleName).
                    setContentText("This the is notification of felix's test").
                    setContentIntent(PendingIntent.getActivity(this,0,Intent(this,javaClass),0)).
                    setAutoCancel(true).
                    build()
                manager.notify(1,notification)
            }
        }
        return true
    }


    override fun finish() {
        super.finish()
        Log.e("felix","finish")
    }

    fun load_toolbar(){
        manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        if(javaClass.simpleName!="MainActivity"){
            setSupportActionBar(findViewById(R.id.sub_tool_bar))
            supportActionBar?.setTitle("")
        val title=findViewById<TextView>(R.id.title)
        title.setText(javaClass.simpleName)
        val go_back=findViewById<Button>(R.id.go_back)
        go_back.setOnClickListener{
            finish()
        }}
        else{
            setSupportActionBar(findViewById(R.id.main_tool_bar))
            supportActionBar?.setTitle("")
        }
    }
}