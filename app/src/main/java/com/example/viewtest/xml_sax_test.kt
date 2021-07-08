package com.example.viewtest


import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.viewtest.newclass.WebUtil
import com.example.viewtest.newclass.saxHandler
import okhttp3.*
import org.xml.sax.InputSource
import java.io.IOException
import java.io.StringReader
import javax.xml.parsers.SAXParserFactory

class xml_sax_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_sax_test)
        load_toolbar()
        val send_and_parse=findViewById<Button>(R.id.send_and_saxparse)
        send_and_parse.setOnClickListener {
            WebUtil.sendOKHttpRequest("http://10.0.2.2/get_data.xml",object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val data=response.body?.string()
                    if(data!=null){
                       parseXMLWithSAX(data)
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("sax","fail")
                }
            }
            )
        }
    }
    fun parseXMLWithSAX(xmlData:String){
        val reader=SAXParserFactory.newInstance().newSAXParser().xmlReader
        val handler=saxHandler()
        reader.contentHandler=handler
        reader.parse(InputSource(StringReader(xmlData)))
    }

}