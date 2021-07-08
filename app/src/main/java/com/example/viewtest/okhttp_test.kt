package com.example.viewtest


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.viewtest.newclass.WebUtil
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

class okhttp_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_test)
        load_toolbar()
        val send_okhttp_button=findViewById<Button>(R.id.send_okhttprequest)
        send_okhttp_button.setOnClickListener {
            WebUtil.sendOKHttpRequest("https://www.baidu.com",object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val data=response.body?.string()
                    if(data!=null){
                        runOnUiThread{
                            findViewById<TextView>(R.id.okhttp_response).text = data
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("okhttp","fail")
                }
            }
            )
        }
    }
}