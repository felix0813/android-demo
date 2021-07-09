package com.example.viewtest.newclass

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuilder

class SaxHandler: DefaultHandler(){
    var nodename =""
    lateinit var id: StringBuilder
    lateinit var name: StringBuilder
    lateinit var version: StringBuilder
    override fun startDocument() {
        id= StringBuilder()
        name= StringBuilder()
        version= StringBuilder()
    }

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        if (localName != null) {
            nodename=localName
        }
        Log.e("saxparser","uri is $uri, localname is $localName, qName is $qName, attributes is $attributes")
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        when(nodename){
            "id"->id.append(ch,start,length)
            "name"->name.append(ch,start,length)
            "version"->version.append(ch,start,length)
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if("app"==localName){
            Log.e("saxparser","id is ${id.toString().trim()}, name is ${name.toString().trim()}, version is ${version.toString().trim()}")
            id.clear()
            name.clear()
            version.clear()
        }
    }
}