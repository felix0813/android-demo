package com.example.viewtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.*

class persistence_test : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persistence_test)
        load_toolbar()
        val store_in_file=findViewById<Button>(R.id.store_in_file)
        store_in_file.setOnClickListener {
            try {
                val output=openFileOutput("data", MODE_APPEND)
                val writer=BufferedWriter(OutputStreamWriter(output))
                val pair=get_edittext()
                if(pair.first.length<=10&&pair.second.length<=10&&!pair.first.contains(' ')&&!pair.second.contains(' ')){
                    val input=openFileInput("data")
                    val reader=BufferedReader(InputStreamReader(input))
                    var repeat=false
                    reader.use {
                        reader.forEachLine {
                            val content=it
                            if(content.contains("${pair.first} ")){
                                repeat=true
                                return@forEachLine
                            }
                        }
                    }
                    if(repeat==false){
                        writer.use {
                            it.write("${pair.first} ${pair.second}\n")
                            Toast.makeText(this,"创建成功",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this,"用户名重复", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(this,"username or password is invalid",Toast.LENGTH_SHORT).show()
                }

            }
            catch (e:IOException){
                e.printStackTrace()
            }
        }
        val sharedPreference=findViewById<Button>(R.id.sharedpreference)
        sharedPreference.setOnClickListener {
            val editor=getSharedPreferences("phone",Context.MODE_PRIVATE).edit()
            val reader=getSharedPreferences("phone",Context.MODE_PRIVATE)
            val pair=get_edittext()
            if(reader.contains(pair.first)){
                AlertDialog.Builder(this).apply {
                    setTitle("名字重复")
                    setMessage("你可以在名字后面加上一个数字来解决这个问题,或者选择替换号码")
                    setPositiveButton("替换"){_,_->
                        editor.putString(pair.first,pair.second)
                        editor.apply()
                    }
                    setNegativeButton("取消"){_,_->
                    }
                    show()
                }
            }
            else{
                editor.putString(pair.first,pair.second)
                editor.apply()
            }


        }
    }
    fun get_edittext():Pair<String,String>{
        val str1=findViewById<EditText>(R.id.register_username).text.toString()
        val str2=findViewById<EditText>(R.id.register_password).text.toString()
        return Pair(str1,str2)
    }
}