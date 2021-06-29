package com.example.viewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.example.viewtest.newclass.NewsRightFrag

class news_content : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_news_content)
        load_toolbar()
        val title=intent.getStringExtra("title")
        val content=intent.getStringExtra("content")
        val fragment=supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsRightFrag
        if (title != null&&content!=null) {
            fragment.refresh(title,content)
        }
    }
}