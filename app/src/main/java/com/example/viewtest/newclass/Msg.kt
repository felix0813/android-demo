package com.example.viewtest.newclass

class Msg(val content:String,val type:Int) {
    companion object{
        const val TYPE_RECEIVE=0
        const val TYPE_SEND=1
    }
}