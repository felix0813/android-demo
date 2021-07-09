package com.example.viewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton


import com.google.android.material.snackbar.Snackbar


class CallbackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_callback)
        load_toolbar()
        val intent= Intent()
        intent.putExtra("Callback","Hello,mid activity.This is callback activity.")

        findViewById<Button>(R.id.giveback).setOnClickListener{
            setResult(RESULT_OK,intent)
            finish()
        }


            findViewById<FloatingActionButton>(R.id.floatingButton).setOnClickListener {
            Snackbar.make(findViewById(R.id.floatingButton),"clicked",Snackbar.LENGTH_SHORT).setAction("Undo"){
                Toast.makeText(this,"click snackbar",Toast.LENGTH_SHORT).show()
            }.show()
        }
    }

}