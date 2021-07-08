package com.example.viewtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*

class login : BaseActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login=findViewById<Button>(R.id.login_button)
        val reader=getPreferences(Context.MODE_PRIVATE)
        val isremembered=reader.getBoolean("isremembered",false)
        if(isremembered){
            findViewById<EditText>(R.id.username).setText(reader.getString("username",""))
            findViewById<EditText>(R.id.password).setText(reader.getString("password",""))
            findViewById<CheckBox>(R.id.remember_password).isChecked=true
        }
        login.setOnClickListener {
            val username=findViewById<EditText>(R.id.username).text.toString()
            val password=findViewById<EditText>(R.id.password).text.toString()
            val reader2=getSharedPreferences("users",Context.MODE_PRIVATE)
            if(reader2.contains(username)&&reader2.getString(username,";;").equals(password)||(username=="felix"&&password=="220813")){
                val checkBox=findViewById<CheckBox>(R.id.remember_password)
                val editor=getPreferences(Context.MODE_PRIVATE).edit()
                editor.putString("current_user",username)
                if (checkBox.isChecked){
                    editor.putBoolean("isremembered",true)
                    editor.putString("username",username)
                    editor.putString("password",password)
                }
                else{
                    editor.clear()
                }
                editor.apply()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this,"username or password is wrong",Toast.LENGTH_LONG).show()
            }
        }
    }
}