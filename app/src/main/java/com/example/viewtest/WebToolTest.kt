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

        binding.webTestButton.start<WebViewTest>()
        binding.httpurlconnectionTestButton.start<HttpurlConnectionTest>()
        binding.okhttpTestButton.start<OkhttpTest>()
        binding.xmlPullTestButton.start<XmlPullTest>()
        binding.xmlSaxTestButton.start<XmlSaxTest>()
        binding.jsonobjectTestButton.start<JsonobjectTest>()
        binding.gsonTestButton.start<GsonTest>()
        binding.retrofitTestButton.start<RetrofitTest>()
    }
}