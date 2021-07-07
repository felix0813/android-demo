package com.example.viewtest


import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class relativelayout_test : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_test)
        load_toolbar()
        val center=findViewById<Button>(R.id.center)
        center.setOnClickListener {
            Toast.makeText(this,"you press center", Toast.LENGTH_SHORT).show()
        }
        val up=findViewById<Button>(R.id.up)
        up.setOnClickListener {
            Toast.makeText(this,"you press up", Toast.LENGTH_SHORT).show()
        }
        val down=findViewById<Button>(R.id.down)
        down.setOnClickListener {
            Toast.makeText(this,"you press down", Toast.LENGTH_SHORT).show()
        }
        val left=findViewById<Button>(R.id.left)
        left.setOnClickListener {
            Toast.makeText(this,"you press left", Toast.LENGTH_SHORT).show()
        }
        val right=findViewById<Button>(R.id.right)
        right.setOnClickListener {
            Toast.makeText(this,"you press right", Toast.LENGTH_SHORT).show()
        }
    }
}