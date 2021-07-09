package com.example.viewtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.edit
import com.example.viewtest.databinding.ActivityChangePasswordDialogBinding

class ChangePasswordDialog : BaseActivity() {
    lateinit var vbinding:ActivityChangePasswordDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vbinding= ActivityChangePasswordDialogBinding.inflate(layoutInflater)
        setContentView(vbinding.root)
        vbinding.ensurePassword.setOnClickListener {
            val read_user=getSharedPreferences("login", Context.MODE_PRIVATE)
            val cur_user=read_user.getString("current_user","null")
            getSharedPreferences("users",Context.MODE_PRIVATE).edit{
                putString(cur_user,vbinding.newPassword.text.toString())
                apply()
            }
            sendBroadcast(Intent("offline"))
        }
        vbinding.cancelChangePassword.setOnClickListener {
            finish()
        }
    }
}