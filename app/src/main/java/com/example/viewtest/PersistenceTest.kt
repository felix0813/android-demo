package com.example.viewtest

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.viewtest.newclass.SqlHelper
import java.io.*

class PersistenceTest : BaseActivity() {

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
                if(pair.first.length<=10&&pair.second.length<=10&&!pair.first.contains(' ')&&!pair.second.contains(' ')&&!(pair.first.equals("")||pair.second.equals(""))){
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
            val editor=getSharedPreferences("users",Context.MODE_PRIVATE).edit()
            val reader=getSharedPreferences("users",Context.MODE_PRIVATE)
            val pair=get_edittext()
            if(reader.contains(pair.first)&&pair.first.equals("")){
                AlertDialog.Builder(this).apply {
                    setTitle("名字重复")
                    setMessage("你需要更换一个用户名")
                    setNegativeButton("OK"){_,_->
                    }
                    show()
                }
            }
            else{
                editor.putString(pair.first,pair.second)
                editor.apply()
            }


        }
        val dbhelper= SqlHelper(this,"db",2).writableDatabase
        val dbstore=findViewById<Button>(R.id.store_in_database)
        dbstore.setOnClickListener {

            val tablename=findViewById<EditText>(R.id.tablename).text.toString()
            val name=findViewById<EditText>(R.id.value1).text.toString()
            val num=findViewById<EditText>(R.id.value2).text.toString()
            if(!name.equals("")&&!num.equals("")) {
                /*val data = ContentValues().apply {
                    put("name", name)
                    put("phone", num)
                    put("id", num.substring(num.length - 4).toInt())
                }
                dbhelper.insert(tablename, null, data)*/
                val sql="insert into contacts (id,name,phone) values (${num.substring(num.length - 4).toInt()},'$name','$num');"
                dbhelper.execSQL(sql)
            }

        }
    }
    fun get_edittext():Pair<String,String>{
        val str1=findViewById<EditText>(R.id.register_username).text.toString()
        val str2=findViewById<EditText>(R.id.register_password).text.toString()
        return Pair(str1,str2)
    }
}
