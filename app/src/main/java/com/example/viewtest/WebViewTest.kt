package com.example.viewtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewTest : BaseActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_test)
        load_toolbar()
        val webview=findViewById<WebView>(R.id.webview)
        webview.settings.javaScriptEnabled=true
        webview.webViewClient= WebViewClient()
        webview.loadUrl("https://www.baidu.com")
    }
}