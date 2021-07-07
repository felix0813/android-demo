package com.example.viewtest


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class okhttp_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_test)
        load_toolbar()
        val send_okhttp_button=findViewById<Button>(R.id.send_okhttprequest)
        send_okhttp_button.setOnClickListener {
            send_okhttp_request()
        }
    }
    fun send_okhttp_request()
    {
        thread {
            val client=OkHttpClient()
            val request=Request.Builder().url("https://www.baidu.com").build()
            val response=client.newCall(request).execute()
            val data=response.body?.string()
            runOnUiThread{
                findViewById<TextView>(R.id.okhttp_response).text = data
            }
        }
    }
}