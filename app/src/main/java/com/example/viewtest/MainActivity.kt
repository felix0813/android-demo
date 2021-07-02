package com.example.viewtest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        load_toolbar()
        val button1: Button =findViewById(R.id.next)


        button1.setOnClickListener{//进入第二个activity
            //Toast.makeText(this,"Button1",Toast.LENGTH_LONG).show()
            val intent= Intent("com.example.activitytest.ACTION_START")
            intent.addCategory("com.example.activitytest.MY_CATEGORY")
            startActivity(intent)
            Log.d("info","click")
        }

        val button4: Button =findViewById(R.id.dial)
        button4.setOnClickListener{

            val tel=findViewById<TextView>(R.id.tel).text.toString()
            if(tel.length!=5&&tel.length!=8&&tel.length!=11){
                val intent2 =Intent(this,tel_warn_activity::class.java)
                intent2.putExtra("tel",tel)
                startActivity(intent2)
            }else{
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1)
                }
                else {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:$tel")
                    startActivity(intent)
                }
            }
            Log.d("info","jump to phone call")
        }

        val life_lauch=findViewById<Button>(R.id.life_lauch)
        life_lauch.setOnClickListener {
            val intent=packageManager.getLaunchIntentForPackage("com.example.lifecycle")
            if (intent != null) {
                intent.putExtra("type","110")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

        }
        val chat_test=findViewById<Button>(R.id.chat_test)
        chat_test.setOnClickListener {
            startActivity(Intent(this,make_9Patch::class.java))
        }
        val news_test_button=findViewById<Button>(R.id.news_test_button)
        news_test_button.setOnClickListener {
            startActivity(Intent(this,news_test::class.java))
        }
        val broadcast_test_button=findViewById<Button>(R.id.broadcast_test_button)
        broadcast_test_button.setOnClickListener {
            startActivity(Intent(this,broadcast_test::class.java))
        }
        val persistence_test_button=findViewById<Button>(R.id.persistence_test_button)
        persistence_test_button.setOnClickListener {
            startActivity(Intent(this,persistence_test::class.java))
        }
        val read_contacts_button=findViewById<Button>(R.id.read_contacts_button)
        read_contacts_button.setOnClickListener {
            startActivity(Intent(this,read_contacts::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    val tel=findViewById<EditText>(R.id.tel).text.toString()
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:$tel")
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"You deny the permission",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}