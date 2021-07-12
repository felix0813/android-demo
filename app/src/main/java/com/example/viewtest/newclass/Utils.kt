package com.example.viewtest.newclass

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.viewbinding.ViewBinding
import com.example.viewtest.BaseActivity

object Utils {
    inline fun <reified T> Button.start() {
        setOnClickListener {
            val intent=Intent(MyAplication.context,T::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            MyAplication.context.startActivity(intent)
        }
    }
    inline fun <reified T> Button.start(crossinline block:Intent.()->Unit) {
        setOnClickListener {
            val intent=Intent(MyAplication.context,T::class.java).apply(block)
            MyAplication.context.startActivity(intent)
        }
    }
    fun Button.toast(activity: BaseActivity){
        setOnClickListener {
            Toast.makeText(activity,"You click the button in ${activity.javaClass.simpleName}!",Toast.LENGTH_SHORT).show()
        }
    }
    fun repeatString(n:Int,string: String):String{
        val result=StringBuilder()
        repeat(n){
            result.append(string)
        }
        return result.toString()
    }
}