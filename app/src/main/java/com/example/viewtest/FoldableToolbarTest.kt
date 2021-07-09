package com.example.viewtest

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.viewtest.databinding.ActivityFoldableToolbarTestBinding
import com.example.viewtest.newclass.Utils.repeatString

class FoldableToolbarTest : BaseActivity() {
    companion object{
        const val fruitId="fruitId"
        const val fruitName="fruitName"
    }
    lateinit var binding:ActivityFoldableToolbarTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFoldableToolbarTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()
       supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val name=intent.getStringExtra(fruitName)?:""
        val id=intent.getIntExtra(fruitId,0)
        supportActionBar?.title=name
        Glide.with(this).load(id).into(binding.fruitImageView)
        binding.fruitContentText.setText(repeatString(1000,name))

    }

}