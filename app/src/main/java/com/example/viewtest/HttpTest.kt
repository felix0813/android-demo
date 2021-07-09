package com.example.viewtest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button


class HttpTest : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_test)
        load_toolbar()
        val website=intent.getStringExtra("extradata")

        Log.d("info",website.toString())

        val button5: Button =findViewById(R.id.http_go)
        button5.setOnClickListener{//响应https网址
            //Toast.makeText(this,"Button1",Toast.LENGTH_LONG).show()
            //finish()
            val intent = Intent(Intent.ACTION_VIEW)

            intent.data= Uri.parse(website)
            startActivity(intent)

        }
    }
}