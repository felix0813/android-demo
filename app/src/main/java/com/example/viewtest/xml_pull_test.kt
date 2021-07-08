package com.example.viewtest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.viewtest.newclass.WebUtil
import okhttp3.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader
import kotlin.concurrent.thread

class xml_pull_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_pull_test)
        load_toolbar()
        val send_and_parse=findViewById<Button>(R.id.send_and_pullparse)
        send_and_parse.setOnClickListener {
            WebUtil.sendOKHttpRequest("http://10.0.2.2/get_data.xml",object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val data=response.body?.string()
                    if(data!=null){
                        parseXMLWithPull(data)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("pull","fail")
                }
            }
            )
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