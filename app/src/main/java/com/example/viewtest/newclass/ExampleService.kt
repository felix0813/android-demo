package com.example.viewtest.newclass

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ExampleService {

    //http://example.com/get_data.json
    @GET("get_data.json")
    fun get_json_data(): Call<Data>

    //http://example.com/<page>/get_data.json
    @GET("{page}/get_data.json")
    fun get_json_data(@Path("page")page :Int):Call<Data>

    //http://example.com/get_data.json?u=<user>&t=<token>
    @GET("get_data.json")
    fun get_json_data(@Query("u")user:String,@Query("t") token:String):Call<Data>

    @DELETE("data/{id}")//通过id删除服务器上的信息
    fun del_byid(@Path("id")id:String):Call<ResponseBody>

    @POST("data/create")
    fun createData(@Body data:Data):Call<ResponseBody>

    @Headers("User-Agent: okhttp","Cache-Control: max-age=0")
    @GET("get_data.json")
    fun headers_get_json_data():Call<Data>

    @GET("get_data.json")
    fun header_get_json_data(@Header("User-agent")userAgent:String,@Header("Cache-Control")cacheControl:String):Call<Data>
}