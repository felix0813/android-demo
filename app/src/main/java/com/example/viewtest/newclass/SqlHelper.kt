package com.example.viewtest.newclass

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SqlHelper(val context : Context, val name :String, val version:Int):
    SQLiteOpenHelper(context,name,null,version){
    private val sql=" create table if not exists contacts(id integer not null unique, name text not null,phone text not null)"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(sql)
        Toast.makeText(context,"create successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}