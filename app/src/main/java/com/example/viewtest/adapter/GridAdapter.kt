package com.example.viewtest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtest.R
import com.example.viewtest.newclass.Fruit

class GridAdapter(val fruit_list:ArrayList<Fruit>) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val fruit_img=view.findViewById<ImageView>(R.id.grid_fruit_img)
        val fruit_name=view.findViewById<TextView>(R.id.grid_fruit_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.staggeredgrid_fruit_item,parent,false)
        val viewHolder=ViewHolder(view)

        return viewHolder
    }



    override fun getItemCount(): Int {
        return fruit_list.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit=fruit_list[position]
        holder.fruit_img.setImageResource(fruit.imageId)
        holder.fruit_name.setText(fruit.name)
        holder.itemView.setOnClickListener{
            Log.e("felix","viewclick")
            //val position1=holder.adapterPosition
            //Log.e("felix","1position${position1}")
            /*val position2=holder.oldPosition
            Log.e("felix","2position${position2}")
            val position3=holder.position
            Log.e("felix","3position${position3}")
            val position4=holder.layoutPosition
            Log.e("felix","4position${position4}")*/

            val fruit=fruit_list[position]
            Toast.makeText(holder.itemView.context,"You click view ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        holder.fruit_img.setOnClickListener{
            Log.e("felix","imgclick")
            //val position=holder.adapterPosition
            val fruit=fruit_list[position]
            Toast.makeText(holder.fruit_img.context,"You click image ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        holder.fruit_name.setOnClickListener{
            Log.e("felix","textclick")
            //val position=holder.adapterPosition
            val fruit=fruit_list[position]
            Toast.makeText(holder.fruit_name.context,"You click text ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
    }
}