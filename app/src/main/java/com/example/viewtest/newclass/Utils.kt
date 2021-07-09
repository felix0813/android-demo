package com.example.viewtest.newclass

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.viewtest.BaseActivity

object Utils {
    inline fun <reified T> Button.start(activity: BaseActivity) {
        setOnClickListener {
            activity.startActivity(Intent(activity,T::class.java))
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