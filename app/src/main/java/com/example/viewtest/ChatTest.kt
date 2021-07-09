package com.example.viewtest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtest.adapter.MsgAdapter
import com.example.viewtest.newclass.Msg

class ChatTest : BaseActivity() {
    private val msg_list= ArrayList<Msg>()
    override fun onCreate(savedInstanceState: Bundle?) {
        msg_list.add(Msg("This is the felix's chat test", Msg.TYPE_RECEIVE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make9_patch)
        load_toolbar()
        val layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val msg_layout=findViewById<RecyclerView>(R.id.msg_layout)

        msg_layout.layoutManager=layoutManager
        val adapter= MsgAdapter(msg_list)
        msg_layout.adapter=adapter
        val send_button=findViewById<Button>(R.id.send_button)
        send_button.setOnClickListener {
            val str=findViewById<EditText>(R.id.inputText).text.toString()

            if(str.isEmpty()){
                Toast.makeText(this,"发送内容不能为空",Toast.LENGTH_SHORT).show()
            }
            else{
                val msg= Msg(str, Msg.TYPE_SEND)
                msg_list.add(msg)
                adapter.notifyItemInserted(msg_list.size-1)
                msg_layout.scrollToPosition(msg_list.size-1)
                findViewById<EditText>(R.id.inputText).setText("")
            }

        }
    }
}