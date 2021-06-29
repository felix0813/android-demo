package com.example.viewtest

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class mid_activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mid_layout)
        load_toolbar()


        val button2: Button =findViewById(R.id.httpstest)
        button2.setOnClickListener{//将网址传给第三个activity

            val intent = Intent(Intent.ACTION_VIEW)
            val https: EditText =findViewById(R.id.httpstext)
            val website= https.text.toString()

            intent.data= Uri.parse(website)
            intent.putExtra("extradata",website)
            startActivity(Intent.createChooser(intent,"choose the open method"))
            Log.d("info","click")
        }
        val button6: Button =findViewById(R.id.httptest)
        button6.setOnClickListener{//将网址传给第三个activity

            val intent = Intent(Intent.ACTION_VIEW)
            val http: EditText = findViewById(R.id.httptext)
            val website=http.text.toString()
            intent.data= Uri.parse(website)
            intent.putExtra("extradata",website)
            startActivity(Intent.createChooser(intent,"choose the open method"))
            Log.d("info","click")
        }
        val layout_test=findViewById<Button>(R.id.layout_test)
        layout_test.setOnClickListener {
            val intent=Intent(this,layout_test_activity::class.java)
            startActivity(intent)
        }
        val img_test: Button =findViewById(R.id.img_test)
        img_test.setOnClickListener {
            val intent=Intent(this,img_test_activity::class.java)
            startActivity(intent)
        }
        val button7: Button =findViewById(R.id.returntest)
        button7.setOnClickListener{
            val intent=Intent(this,CallbackActivity::class.java)

            startActivityForResult(intent,1)
        }
        val list_view=findViewById<Button>(R.id.list_view_test)
        list_view.setOnClickListener {
            val intent=Intent(this,list_view_test::class.java)
            startActivity(intent)
        }
        val recyclerview=findViewById<Button>(R.id.recyclerview_test)
        recyclerview.setOnClickListener {
            val intent=Intent(this,recyclerview_test::class.java)
            startActivity(intent)
        }
        val staggeredGrid=findViewById<Button>(R.id.staggeredGrid)
        staggeredGrid.setOnClickListener{
            val intent=Intent(this,staggeredGrid_test::class.java)
            startActivity(intent)
        }
        val grid_layout=findViewById<Button>(R.id.grid_layout_button)
        grid_layout.setOnClickListener{
            val intent=Intent(this,grid_test::class.java)
            startActivity(intent)
        }
        val frag_test=findViewById<Button>(R.id.fragment_test)
        frag_test.setOnClickListener {
            val intent=Intent(this,Fragment_test::class.java)
            startActivity(intent)
        }
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