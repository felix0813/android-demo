package com.example.viewtest

import android.content.Intent

import android.os.Bundle
import android.widget.Button

class webtool_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webtool_test)
        load_toolbar()
        val web_test_button=findViewById<Button>(R.id.web_test_button)
        web_test_button.setOnClickListener {
            startActivity(Intent(this,web_test::class.java))
        }
        val http_url_button=findViewById<Button>(R.id.httpurlconnection_test_button)
        http_url_button.setOnClickListener {
            startActivity(Intent(this,httpurlconnection_test::class.java))
        }
        val okhttp_test_button=findViewById<Button>(R.id.okhttp_test_button)
        okhttp_test_button.setOnClickListener {
            startActivity(Intent(this,okhttp_test::class.java))
        }
        val xml_pull_test_button=findViewById<Button>(R.id.xml_pull_test_button)
        xml_pull_test_button.setOnClickListener {
            startActivity(Intent(this,xml_pull_test::class.java))
        }
        val xml_sax_test_button=findViewById<Button>(R.id.xml_sax_test_button)
        xml_sax_test_button.setOnClickListener {
            startActivity(Intent(this,xml_sax_test::class.java))
        }
        val jsonobject_test_button=findViewById<Button>(R.id.jsonobject_test_button)
        jsonobject_test_button.setOnClickListener {
            startActivity(Intent(this,jsonobject_test::class.java))
        }
        val gson_test_button=findViewById<Button>(R.id.gson_test_button)
        gson_test_button.setOnClickListener {
            startActivity(Intent(this,gson_test::class.java))
        }
        val retrofit_test_button=findViewById<Button>(R.id.retrofit_test_button)
        retrofit_test_button.setOnClickListener {
            startActivity(Intent(this,retrofit_test::class.java))
        }
    }
}