package com.example.viewtest


import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.viewtest.newclass.App
import com.example.viewtest.newclass.WebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException


class GsonTest : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_test)
        load_toolbar()
        val send_gson=findViewById<Button>(R.id.send_and_gson)
        send_gson.setOnClickListener {
            WebUtil.sendOKHttpRequest("http://10.0.2.2/get_data.json",object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val data=response.body?.string()
                    if(data!=null){
                        parseWithGson(data)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("gson","fail")
                }
            }
            )
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