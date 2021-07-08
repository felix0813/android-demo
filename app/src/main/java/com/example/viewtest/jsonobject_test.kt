package com.example.viewtest


import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.viewtest.newclass.WebUtil
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class jsonobject_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jsonobject_test)
        load_toolbar()
        val send_and_jsonobject=findViewById<Button>(R.id.send_and_jsonobject)
        send_and_jsonobject.setOnClickListener {
            WebUtil.sendOKHttpRequest("http://10.0.2.2/get_data.json",object :Callback{
                override fun onResponse(call: Call, response: Response) {
                    val data=response.body?.string()
                    if(data!=null){
                        parseWithJsonObject(data)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("jsonobject","fail")
                }
            }
            )
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