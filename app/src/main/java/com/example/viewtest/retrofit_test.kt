package com.example.viewtest

import android.os.Bundle
import android.util.Log
import com.example.viewtest.databinding.ActivityRetrofitTestBinding
import com.example.viewtest.newclass.App
import com.example.viewtest.newclass.AppService
import com.example.viewtest.newclass.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class retrofit_test : BaseActivity() {
    lateinit var binding:ActivityRetrofitTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRetrofitTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()
        binding.getAppData.setOnClickListener {
            val appservice=ServiceCreator.create<AppService>("http://10.0.0.2/")
            appservice.get_json_data().enqueue(object :Callback<List<App>>{
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list=response.body()
                    if(list!=null){
                        for(tmp in list){
                            Log.e("retrofit","id is ${tmp.id}, name is ${tmp.name}, version is ${tmp.version}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    Log.e("retrofit","fail")
                }
            })
        }
    }
}