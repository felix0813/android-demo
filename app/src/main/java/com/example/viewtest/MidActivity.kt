package com.example.viewtest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.viewtest.databinding.ActivityMidBinding
import com.example.viewtest.newclass.Utils.start

class MidActivity : BaseActivity() {
    lateinit var binding:ActivityMidBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()
        binding.midFloat.floatingButton.bringToFront()
        binding.midFloat.floatingButton.setOnClickListener {
            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
        }
        binding.dial.setOnClickListener{
            val tel=findViewById<TextView>(R.id.tel).text.toString()
            if(tel.length!=5&&tel.length!=8&&tel.length!=11){
                val intent2 =Intent(this,TelNumWarn::class.java)
                intent2.putExtra("tel",tel)
                startActivity(intent2)
            }else{
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
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

        binding.httpstest.setOnClickListener{//将网址传给第三个activity
            val intent = Intent(Intent.ACTION_VIEW)
            val https: EditText =findViewById(R.id.httpstext)
            val website= https.text.toString()

            intent.data= Uri.parse(website)
            intent.putExtra("extradata",website)
            startActivity(Intent.createChooser(intent,"choose the open method"))
            Log.d("info","click")
        }
        binding.httptest.setOnClickListener{//将网址传给第三个activity
            val intent = Intent(Intent.ACTION_VIEW)
            val http: EditText = findViewById(R.id.httptext)
            val website=http.text.toString()
            intent.data= Uri.parse(website)
            intent.putExtra("extradata",website)
            startActivity(Intent.createChooser(intent,"choose the open method"))
            Log.d("info","click")
        }

        binding.layoutTest.start<RelativelayoutTest>(this)
        binding.imgTest.start<ImageTest>(this)
        val button7: Button =findViewById(R.id.returntest)
        button7.setOnClickListener{
            val intent=Intent(this,CallbackActivity::class.java)

            startActivityForResult(intent,1)
        }

        binding.listViewTest.start<ListViewTest>(this)
        binding.recyclerviewTest.start<RecyclerviewTest>(this)
        binding.staggeredGrid.start<StaggeredGridTest>(this)
        binding.gridLayoutButton.start<GridTest>(this)
        binding.fragmentTest.start<Fragment_test>(this)
        binding.cardViewTest.start<CardViewTest>(this)
        //binding.foldableToolbarTest.start<FoldableToolbarTest>(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1-> if(resultCode== RESULT_OK){
                val callback=data?.getStringExtra("Callback")
                Log.d("Callback",callback.toString())
                Toast.makeText(this,callback.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

}