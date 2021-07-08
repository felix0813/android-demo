package com.example.viewtest.newclass

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    inline fun <reified T> create(url:String):T=
        Retrofit.Builder().baseUrl("url").addConverterFactory(GsonConverterFactory.create()).build().create(T::class.java)
}