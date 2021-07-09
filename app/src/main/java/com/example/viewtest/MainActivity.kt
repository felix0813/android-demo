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
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.viewtest.databinding.ActivityMainBinding
import com.example.viewtest.newclass.AllService
import com.example.viewtest.newclass.Utils.start
import com.google.android.material.navigation.NavigationView

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
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        load_toolbar()
        val read_user=getSharedPreferences("login",Context.MODE_PRIVATE)
        val take_drawer_out=findViewById<ImageButton>(R.id.main_drawer_button)
        take_drawer_out.setOnClickListener {
            val drawerlayout=findViewById<DrawerLayout>(R.id.main_drawerlayout)
            drawerlayout.openDrawer(GravityCompat.START)
        }
        binding.next.setOnClickListener{//进入第二个activity
            //Toast.makeText(this,"Button1",Toast.LENGTH_LONG).show()
            val intent= Intent("com.example.activitytest.ACTION_START")
            intent.addCategory("com.example.activitytest.MY_CATEGORY")
            startActivity(intent)
            Log.d("info","click")
        }



       binding.lifeLaunch.setOnClickListener {
            val intent=packageManager.getLaunchIntentForPackage("com.example.lifecycle")
            if (intent != null) {
                intent.putExtra("type","110")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

        }
        binding.chatTest.start<ChatTest>(this)
        binding.newsTestButton.start<news_test>(this)
        binding.broadcastTestButton.start<BroadcastTest>(this)
        binding.persistenceTestButton.start<persistence_test>(this)
        binding.readContactsButton.start<read_contacts>(this)
        binding.cameraTestButton.start<CameraTest>(this)
        binding.multimediaButton.start<multimedia_test>(this)
        val web_relative_test_button=findViewById<Button>(R.id.webtool_test_button)
        web_relative_test_button.setOnClickListener {
            startActivity(Intent(this,webtool_test::class.java))
        }

        val navView=findViewById<NavigationView>(R.id.navView)
        val header=navView.getHeaderView(0)
        header.findViewById<TextView>(R.id.user_text).setText(read_user.getString("current_user","null"))

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.cancel_user_item->{
                    AlertDialog.Builder(this).setTitle("警告").setMessage("你确认要注销账号？").setNegativeButton("取消",null).
                    setPositiveButton("确认"){_,_->
                        getSharedPreferences("users",Context.MODE_PRIVATE).edit(){
                            remove(read_user.getString("current_user","null"))
                            apply()
                        }
                        sendBroadcast(Intent("offline"))
                    }.show()
                    true
                }
                R.id.change_password_item->{
                    startActivity(Intent(this,ChangePasswordDialog::class.java))
                    true
                }
                else-> true
            }
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