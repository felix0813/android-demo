package com.example.viewtest.newclass

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viewtest.R

class NewsRightFrag:Fragment() {
    private lateinit var fcontainer:ViewGroup
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container != null) {
            fcontainer=container
        }
        else{
            Log.e("felix","container is null")
        }
        return inflater.inflate(R.layout.news_right_frag,container,false)
    }
    @SuppressLint("InflateParams")
    fun refresh(title:String, content:String){
        Log.e("felix","refresh")
        val textView1= view?.findViewById<TextView>(R.id.news_title_text)
        if (textView1 != null) {
            textView1.text=title
        }
        val textView2= view?.findViewById<TextView>(R.id.news_content_text)
        if (textView2 != null) {
            textView2.text=content
        }
    }

}