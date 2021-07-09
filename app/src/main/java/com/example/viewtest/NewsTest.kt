package com.example.viewtest


import android.os.Bundle

class NewsTest : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_test)
        load_toolbar()
    }


}
