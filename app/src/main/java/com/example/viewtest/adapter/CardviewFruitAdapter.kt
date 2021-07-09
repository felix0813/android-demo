package com.example.viewtest.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewtest.R
import com.example.viewtest.newclass.Fruit

class CardviewFruitAdapter(val context: Context, val fruit_list:ArrayList<Fruit>):RecyclerView.Adapter<CardviewFruitAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val fruit_img=view.findViewById<ImageView>(R.id.cardviewFruitImage)
        val fruit_name=view.findViewById<TextView>(R.id.cardviewFruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.cardview_fruit_item,parent,false)
        val viewHolder=ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit=fruit_list[position]
        Glide.with(context).load(fruit.imageId).into(holder.fruit_img)
        holder.fruit_name.setText(fruit.name)
    }

    override fun getItemCount()=fruit_list.size
}