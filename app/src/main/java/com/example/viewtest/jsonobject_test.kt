package com.example.viewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import kotlin.concurrent.thread

class jsonobject_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jsonobject_test)
        load_toolbar()
        val send_and_jsonobject=findViewById<Button>(R.id.send_and_jsonobject)
        send_and_jsonobject.setOnClickListener {
            sendokhttp()
        }
    }
    fun sendokhttp(){
        thread{
            val request = Request.Builder().url("http://10.0.2.2/get_data.json").build()
            val client = OkHttpClient()
            val response = client.newCall(request).execute()
            val data = response.body?.string()
            if (data != null) {
                parseWithJsonObject(data)
            }
        }
    }
    fun parseWithJsonObject(data:String){
        val jsonArray=JSONArray(data)
        for(i in 0 until jsonArray.length()){
            val jsonObject=jsonArray.getJSONObject(i)
            val id=jsonObject.getString("id")
            val name=jsonObject.getString("name")
            val version=jsonObject.getString("version")
            Log.e("jsonobject","id is $id, name is $name, version is $version")
        }
    }
}