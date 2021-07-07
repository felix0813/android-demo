package com.example.viewtest


import android.os.Bundle
import android.widget.Button
import com.example.viewtest.newclass.saxHandler
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import java.io.StringReader
import java.lang.StringBuilder
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class xml_sax_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_sax_test)
        load_toolbar()
        val send_and_parse=findViewById<Button>(R.id.send_and_saxparse)
        send_and_parse.setOnClickListener {
            thread {
                val client= OkHttpClient()
                val request= Request.Builder().url("http://10.0.2.2/get_data.xml").build()
                val response=client.newCall(request).execute()
                val data=response.body?.string()
                if(data!=null){
                    parseXMLWithSAX(data)
                }
            }
        }
    }
    fun parseXMLWithSAX(xmlData:String){
        val reader=SAXParserFactory.newInstance().newSAXParser().xmlReader
        val handler=saxHandler()
        reader.contentHandler=handler
        reader.parse(InputSource(StringReader(xmlData)))
    }

}