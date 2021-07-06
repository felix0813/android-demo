package com.example.viewtest.newclass

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {
    var sqlHelper:sqlhelper?=null
    val contactsDir=0
    val contactsItem=1

    val authority="com.example.viewtest.provider"
    private val uriMatcher by lazy{
        val uriMatcher=UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher.addURI(authority,"contacts",contactsDir)
        uriMatcher.addURI(authority,"contacts/#",contactsItem)
        uriMatcher
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?)=
        sqlHelper?.let {
            val db=it.writableDatabase
            val rows=when(uriMatcher.match(uri)){
                contactsDir->db.delete("contacts",selection,selectionArgs)
                contactsItem->db.delete("contacts","id = ?", arrayOf(uri.pathSegments[1]))
                else->0
            }
            rows
        }?:0



    override fun getType(uri: Uri)=when(uriMatcher.match(uri)){
        contactsDir->"vnd.android.cursor.dir/vnd.com.example.viewtest.provider.contacts"
        contactsItem->"vnd.android.cursor.item/vnd.com.example.viewtest.provider.contacts"
        else->null

    }

    override fun insert(uri: Uri, values: ContentValues?) =
        sqlHelper?.let {
            val db=it.writableDatabase
            val result=when(uriMatcher.match(uri)){
                contactsDir,contactsItem->{
                    db.insert("contacts",null,values)
                    val newid=values?.get("id")
                    Uri.parse("content://$authority/contacts/$newid")
                }
                else->null
            }
            result
        }

    override fun onCreate()=context?.let{
        sqlHelper= context?.let { sqlhelper(it,"db",2) }
        true
    }?:false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    )=sqlHelper?.let {
        val db=it.writableDatabase
        val cursor=when(uriMatcher.match(uri)){
            contactsDir->db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder)
            contactsItem->db.query("contacts",projection,"id = ?", arrayOf(uri.pathSegments[1]),null,null,sortOrder)
            else->null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    )=sqlHelper?.let {
        val db=it.writableDatabase
        val rows=when(uriMatcher.match(uri)){
            contactsDir->db.update("contacts",values,selection,selectionArgs)
            contactsItem->db.update("contacts",values,"id = ?", arrayOf(uri.pathSegments[1]))
            else->0
        }
        rows
    }?:0
}