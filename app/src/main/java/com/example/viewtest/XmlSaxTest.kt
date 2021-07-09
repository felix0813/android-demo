package com.example.viewtest


import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.viewtest.newclass.WebUtil
import com.example.viewtest.newclass.SaxHandler
import okhttp3.*
import org.xml.sax.InputSource
import java.io.IOException
import java.io.StringReader
import javax.xml.parsers.SAXParserFactory

class XmlSaxTest : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_sax_test)
        load_toolbar()
        val sendAndParse=findViewById<Button>(R.id.send_and_saxparse)
        sendAndParse.setOnClickListener {
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
        val handler=SaxHandler()
        reader.contentHandler=handler
        reader.parse(InputSource(StringReader(xmlData)))
    }

}