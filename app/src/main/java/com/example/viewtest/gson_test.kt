package com.example.viewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.viewtest.newclass.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import kotlin.concurrent.thread

class gson_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_test)
        load_toolbar()
        val send_gson=findViewById<Button>(R.id.send_and_gson)
        send_gson.setOnClickListener {
            thread {
                sendokhttp()
            }
        }
    }
    fun sendokhttp(){
        thread{
            val request = Request.Builder().url("http://10.0.2.2/get_data.json").build()
            val client = OkHttpClient()
            val response = client.newCall(request).execute()
            val data = response.body?.string()
            if (data != null) {
                parseWithGson(data)
            }
        }
    }
    fun parseWithGson(data:String){
        val gson=Gson()
        val typeOf=object : TypeToken<List<App>>(){}.type
        val appList=gson.fromJson<List<App>>(data,typeOf)
        for(tmp in appList){
            val id=tmp.id
            val name=tmp.name
            val version=tmp.version
            Log.e("gson","id is $id, name is $name, version is $version")
        }
    }
}