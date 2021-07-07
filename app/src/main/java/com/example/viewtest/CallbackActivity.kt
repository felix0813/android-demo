package com.example.viewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button


class CallbackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback)
        load_toolbar()
        val intent= Intent()
        intent.putExtra("Callback","Hello,mid activity.This is callback activity.")

        val button8: Button =findViewById(R.id.giveback)
        button8.setOnClickListener{
            setResult(RESULT_OK,intent)
            finish()
        }
    }

}