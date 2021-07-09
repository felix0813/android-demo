package com.example.viewtest.newclass

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtest.R
import com.example.viewtest.NewsContent


class NewsLeftFrag:Fragment() {
    companion object{
        public var isTwoPane=false
    }


    inner class NewsAdapter(val news_list:ArrayList<News>,val context: FragmentActivity):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val title=view.findViewById<TextView>(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)

            val holder=ViewHolder(view)

            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val news=news_list[position]
                if(isTwoPane){
                    Log.e("felix","is two pane")
                    val fragment=context.supportFragmentManager.findFragmentById(R.id.news_content_frag) as NewsRightFrag
                    fragment.refresh(news.title,news.content)
                }
                else{
                    Log.e("felix","is one pane")
                    val intent= Intent(context, NewsContent::class.java)
                    intent.putExtra("title",news.title)
                    intent.putExtra("content",news.content)
                    context.startActivity(intent)
                }
            }
            val news=news_list[position]
            holder.title.text=news.title
        }

        override fun getItemCount()=news_list.size
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_left_frag,container,false)
    }

    @SuppressLint("CutPasteId")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.e("felix","onactivitycreated")
        super.onActivityCreated(savedInstanceState)
        isTwoPane=activity?.findViewById<View>(R.id.news_content_frag)!=null
        Log.e("felix",isTwoPane.toString())
        val layoutManager=LinearLayoutManager(activity)
        activity?.findViewById<RecyclerView>(R.id.news_list)?.layoutManager =layoutManager
        val adapter= activity?.let { NewsAdapter(getnews(), it) }
        activity?.findViewById<RecyclerView>(R.id.news_list)?.adapter=adapter
    }
    fun getnews():ArrayList<News>{
        Log.e("felix","gotnews")
        val newsList=ArrayList<News>()
        for(i in 1..50){
            val news=News("title${i}","content${i}")
            newsList.add(news)
        }
        return newsList
    }
}