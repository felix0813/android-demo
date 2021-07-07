package com.example.viewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class httpurlconnection_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_httpurlconnection_test)
        load_toolbar()
        val send_httprequest_button= findViewById<Button>(R.id.sendhttprequest)
        send_httprequest_button.setOnClickListener {
            sendRequestWithHttpUrlConnection()
        }
    }
    fun sendRequestWithHttpUrlConnection(){
        thread {
            var connection : HttpURLConnection? =null
            try {
                val response=StringBuilder()

                val url=URL("https://www.baidu.com")
                connection=url.openConnection().apply {
                    readTimeout=8000
                    connectTimeout=8000
                } as HttpURLConnection
                val input=connection.inputStream
                val reader=BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                runOnUiThread{
                    findViewById<TextView>(R.id.responsetext).text=response
                }
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
        }
    }
}