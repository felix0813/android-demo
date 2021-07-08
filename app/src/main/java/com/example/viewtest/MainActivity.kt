package com.example.viewtest

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.viewtest.databinding.ActivityMainBinding
import com.example.viewtest.newclass.AllService

class MainActivity : BaseActivity() {
    lateinit var binder: Binder
    val connection=object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder=service as Binder
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        bindService(Intent(this, AllService::class.java),connection, Context.BIND_AUTO_CREATE)
        setContentView(R.layout.activity_main)
        load_toolbar()
        val read_user=getSharedPreferences("login",Context.MODE_PRIVATE)
        val drawertext=findViewById<TextView>(R.id.drawer_text)
        val newtext="当前用户：${read_user.getString("current_user","fail")}"
        drawertext.text=newtext

        val change_password=findViewById<Button>(R.id.change_password)
        change_password.setOnClickListener {
            startActivity(Intent(this,change_password_dialog::class.java))
        }

        val take_drawer_out=findViewById<ImageButton>(R.id.main_drawer_button)
        take_drawer_out.setOnClickListener {
            val drawerlayout=findViewById<DrawerLayout>(R.id.main_drawerlayout)
            drawerlayout.openDrawer(GravityCompat.START)
        }
        val button1: Button =findViewById(R.id.next)


        button1.setOnClickListener{//进入第二个activity
            //Toast.makeText(this,"Button1",Toast.LENGTH_LONG).show()
            val intent= Intent("com.example.activitytest.ACTION_START")
            intent.addCategory("com.example.activitytest.MY_CATEGORY")
            startActivity(intent)
            Log.d("info","click")
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
        val chat_test_button=findViewById<Button>(R.id.chat_test)
        chat_test_button.setOnClickListener {
            startActivity(Intent(this,chat_test::class.java))
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
        val camera_test=findViewById<Button>(R.id.camera_test_button)
        camera_test.setOnClickListener {
            startActivity(Intent(this,com.example.viewtest.camera_test::class.java))
        }
        val multimedia_button=findViewById<Button>(R.id.multimedia_button)
            multimedia_button.setOnClickListener{
                startActivity(Intent(this,multimedia_test::class.java))
        }
        val web_relative_test_button=findViewById<Button>(R.id.webtool_test_button)
        web_relative_test_button.setOnClickListener {
            startActivity(Intent(this,webtool_test::class.java))
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