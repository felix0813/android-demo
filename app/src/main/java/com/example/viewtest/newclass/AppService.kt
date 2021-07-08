package com.example.viewtest.newclass


import retrofit2.Call
import retrofit2.http.GET

interface AppService {

    @GET("get_data.json")
    fun get_json_data(): Call<List<App>>

    @GET("get_data.xml")
    fun get_xml_data():Call<List<App>>
}