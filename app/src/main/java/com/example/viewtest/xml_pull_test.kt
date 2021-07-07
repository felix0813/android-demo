package com.example.viewtest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import kotlin.concurrent.thread

class xml_pull_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_pull_test)
        load_toolbar()
        val send_and_parse=findViewById<Button>(R.id.send_and_pullparse)
        send_and_parse.setOnClickListener {
        thread {
            val client=OkHttpClient()
            val request=Request.Builder().url("http://10.0.2.2/get_data.xml").build()
            val response=client.newCall(request).execute()
            val data=response.body?.string()
            if(data!=null){
                parseXMLWithPull(data)
            }
        }
        }
    }
    fun parseXMLWithPull(xmldata:String){
        val factory=XmlPullParserFactory.newInstance()
        val xmlPullParser=factory.newPullParser()
        xmlPullParser.setInput(StringReader(xmldata))
        var eventType=xmlPullParser.eventType
        var id="null"
        var name="null"
        var version="null"
        while(eventType!=XmlPullParser.END_DOCUMENT){
            val nodename=xmlPullParser.name
            when(eventType){
                XmlPullParser.START_TAG->{
                    when(nodename){
                        "id"->id=xmlPullParser.nextText()
                        "name"->name=xmlPullParser.nextText()
                        "version"->version=xmlPullParser.nextText()
                    }
                }
                XmlPullParser.END_TAG->{
                    if("app"==nodename){
                        Log.e("pullparse","id is $id, name is $name, version is $version")
                    }
                }
            }
            eventType=xmlPullParser.next()
        }
    }
}