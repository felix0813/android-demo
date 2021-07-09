package com.example.viewtest

import android.os.Bundle
import com.example.viewtest.databinding.ActivityWebtoolTestBinding
import com.example.viewtest.newclass.Utils.start

class WebToolTest : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityWebtoolTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()

        binding.webTestButton.start<WebViewTest>(this)
        binding.httpurlconnectionTestButton.start<HttpurlConnectionTest>(this)
        binding.okhttpTestButton.start<OkhttpTest>(this)
        binding.xmlPullTestButton.start<XmlPullTest>(this)
        binding.xmlSaxTestButton.start<XmlSaxTest>(this)
        binding.jsonobjectTestButton.start<JsonobjectTest>(this)
        binding.gsonTestButton.start<GsonTest>(this)
        binding.retrofitTestButton.start<RetrofitTest>(this)
    }
}