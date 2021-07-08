package com.example.viewtest.newclass

import okhttp3.OkHttpClient
import okhttp3.Request

object WebUtil {
    fun sendOKHttpRequest(addr:String,callback:okhttp3.Callback){
        val client=OkHttpClient()
        val request= Request.Builder().url(addr).build()
        client.newCall(request).enqueue(callback)
    }
}