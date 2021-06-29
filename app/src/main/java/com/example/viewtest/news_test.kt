package com.example.viewtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.example.viewtest.newclass.NewsLeftFrag


class news_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_news_test)
        load_toolbar()


    }
    fun change_textview(title:String,content:String) {

        if (NewsLeftFrag.isTwoPane) {
            val fragment = supportFragmentManager.findFragmentById(R.id.news_content_frag)
            fragment?.view?.findViewById<TextView>(R.id.news_title_text)?.setText(title)
            fragment?.view?.findViewById<TextView>(R.id.news_content_text)?.setText(content)
        }
    }

    companion object {

    }
}
