package com.example.viewtest

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class TelNumWarn : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tel_warn)

        val warn_info: TextView =findViewById(R.id.warn_info)
        val tel=intent.getStringExtra("tel")

        warn_info.setText("输入的电话号码为${tel?.length} 位,确认拨打")

        val continue_dial: Button =findViewById(R.id.continue_dial)
        continue_dial.setOnClickListener {
            val intent= Intent(Intent.ACTION_DIAL)
            intent.data= Uri.parse("tel:$tel")
            startActivity(intent)
            finish()
        }
        val reinput=findViewById<Button>(R.id.reinput)
        reinput.setOnClickListener {
            finish()
        }
    }
}