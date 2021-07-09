package com.example.viewtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.example.viewtest.databinding.ActivityLoginBinding

class Login : BaseActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val login=findViewById<Button>(R.id.login_button)
        val reader=getPreferences(Context.MODE_PRIVATE)
        val isremembered=reader.getBoolean("isremembered",false)
        if(isremembered){
            binding.username.setText(reader.getString("username",""))
            binding.password.setText(reader.getString("password",""))
            binding.rememberPassword.isChecked=true
        }
        val reader2=getSharedPreferences("users",Context.MODE_PRIVATE)
        binding.firstLogin.setOnClickListener {
            if(!reader2.contains("admin")){
                getSharedPreferences("users",Context.MODE_PRIVATE).edit().apply{
                    putString("admin","12345")
                    apply()
                }
                Toast.makeText(this,"默认用户名为admin，默认密码为12345",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"不是第一次登录",Toast.LENGTH_SHORT).show()
            }
        }
        login.setOnClickListener {
            val username=findViewById<EditText>(R.id.username).text.toString()
            val password=findViewById<EditText>(R.id.password).text.toString()

            if(reader2.contains(username)&&reader2.getString(username,";;").equals(password)){
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