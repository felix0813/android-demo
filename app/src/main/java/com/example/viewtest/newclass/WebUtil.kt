package com.example.viewtest.newclass

import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object WebUtil {
    fun sendOKHttpRequest(addr:String,callback:Callback){
        val client=OkHttpClient()
        val request= Request.Builder().url(addr).build()
        client.newCall(request).enqueue(callback)
    }
    fun sendOKHttpRequest(addr:String,callback:Callback,block:(String)->Unit){
        val client=OkHttpClient()
        val request= Request.Builder().url(addr).build()
        client.newCall(request).enqueue(callback)
    }
    suspend fun sendRequest(addr: String):String{
         return suspendCoroutine { continuation ->
             sendOKHttpRequest(addr,object :Callback{
                 override fun onFailure(call: Call, e: IOException) {
                     continuation.resumeWithException(e)
                 }
                 override fun onResponse(call: Call, response: Response) {
                     val data=response.body?.string() as String
                     continuation.resume(data)
                 }
             })
         }
    }
}