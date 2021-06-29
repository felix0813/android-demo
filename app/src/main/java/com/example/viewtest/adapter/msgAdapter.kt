package com.example.viewtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtest.newclass.Msg
import com.example.viewtest.R

class msgAdapter(val msgList:ArrayList<Msg>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class leftViewHolder(view: View):RecyclerView.ViewHolder(view){
        val leftMsg:TextView=view.findViewById(R.id.leftMsg)
    }
    inner class rightViewHolder(view: View):RecyclerView.ViewHolder(view){
        val rightMsg:TextView=view.findViewById(R.id.rightMsg)
    }
    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun getItemViewType(position: Int)=msgList[position].type
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg=msgList[position]
        when(holder){
            is leftViewHolder ->holder.leftMsg.text=msg.content
            is rightViewHolder ->holder.rightMsg.text=msg.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType== Msg.TYPE_RECEIVE){
            val view=LayoutInflater.from(parent.context).inflate(R.layout.msg_left,parent,false)
            return leftViewHolder(view)
        }
        else{
            val view=LayoutInflater.from(parent.context).inflate(R.layout.msg_right,parent,false)
            return rightViewHolder(view)
        }
    }
}