package com.example.viewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.viewtest.databinding.ActivityCallbackBinding
import com.google.android.material.snackbar.Snackbar


class CallbackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding=ActivityCallbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()
        val intent= Intent()
        intent.putExtra("Callback","Hello,mid activity.This is callback activity.")

        binding.giveback.setOnClickListener{
            setResult(RESULT_OK,intent)
            finish()
        }
        binding.include.floatingButton.setOnClickListener {
            Snackbar.make(binding.giveback,"clicked",Snackbar.LENGTH_SHORT).setAction("Undo"){
                Toast.makeText(this,"click snackbar",Toast.LENGTH_SHORT).show()
            }.show()
        }
    }

}