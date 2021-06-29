package com.example.viewtest.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.viewtest.newclass.Fruit
import com.example.viewtest.R

class FruitAdapter(activity:Activity,val resourceId:Int,arr:List<Fruit>) : ArrayAdapter<Fruit>(activity,resourceId,arr) {
    inner class ViewHolder(val fruitImage:ImageView,val fruitName:TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view:View
        val viewholder: ViewHolder
        if(convertView==null){
            view=LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage:ImageView=view.findViewById(R.id.fruit_img)
            val fruitName:TextView=view.findViewById(R.id.fruit_name)
            viewholder= ViewHolder(fruitImage, fruitName)
            view.tag=viewholder

            }
        else{
            view=convertView
            viewholder=view.tag as ViewHolder
        }

        val fruit=getItem(position)
        if(fruit!=null){
            viewholder.fruitImage.setImageResource(fruit.imageId)
            viewholder.fruitName.text=fruit.name
        }
        return view
    }
}