package com.example.viewtest

import android.content.Intent

import android.os.Bundle
import android.widget.Button
import com.example.viewtest.databinding.ActivityWebtoolTestBinding
import com.example.viewtest.newclass.Utils.start

class webtool_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityWebtoolTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()

        binding.webTestButton.start<web_test>(this)
        binding.httpurlconnectionTestButton.start<httpurlconnection_test>(this)
        binding.okhttpTestButton.start<okhttp_test>(this)
        binding.xmlPullTestButton.start<xml_pull_test>(this)
        binding.xmlSaxTestButton.start<xml_sax_test>(this)
        binding.jsonobjectTestButton.start<jsonobject_test>(this)
        binding.gsonTestButton.start<gson_test>(this)
        binding.retrofitTestButton.start<retrofit_test>(this)
    }
}