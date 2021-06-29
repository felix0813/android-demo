package com.example.viewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.viewtest.newclass.AnotherRightFragment
import com.example.viewtest.newclass.NullFragment
import com.example.viewtest.newclass.RightFragment

class Fragment_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
        load_toolbar()

        val fragmentManager=supportFragmentManager
        val transaction0=fragmentManager.beginTransaction()
        transaction0.replace(R.id.righlayout,NullFragment())
        transaction0.commit()
        val button1=findViewById<Button>(R.id.fragment_left_button1)

        button1.setOnClickListener {
            val transaction=fragmentManager.beginTransaction()
            transaction.replace(R.id.righlayout,RightFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        val button2=findViewById<Button>(R.id.fragment_left_button2)
        button2.setOnClickListener {
            val transaction=fragmentManager.beginTransaction()
            transaction.replace(R.id.righlayout,AnotherRightFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }


}