package com.example.viewtest

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.viewtest.databinding.ActivityMainBinding

open class BaseActivity : AppCompatActivity() {
    private lateinit var receiver:offlineReceiver
    private lateinit var the_menu: Menu

    inner class offlineReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("下线通知")
                setMessage("您已经被强制下线，请重新登陆")
                setCancelable(false)
                setPositiveButton("OK"){_,_ ->
                    ActivityCollector.exit()
                    startActivity(Intent(context,login::class.java))
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
        Log.e("felix","menu")
        menuInflater.inflate(R.menu.main,menu)
        if (menu != null) {
            the_menu=menu
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item-> Toast.makeText(this,"Add", Toast.LENGTH_LONG).show()
            R.id.remove_item-> Toast.makeText(this,"Remove", Toast.LENGTH_LONG).show()
            R.id.exit->ActivityCollector.exit()
            R.id.offline->sendBroadcast(Intent("offline"))

        }

        return true
    }


    override fun finish() {
        super.finish()
        Log.e("felix","finish")
    }

    fun load_toolbar(){
        if(javaClass.simpleName!="MainActivity"){
            setSupportActionBar(findViewById(R.id.sub_tool_bar))
            supportActionBar?.setTitle("")

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