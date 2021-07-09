package com.example.viewtest

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtest.adapter.RecyclerFruitAdapter
import com.example.viewtest.newclass.Fruit


class recyclerview_test : BaseActivity() {
    private var fruit_list= ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_test)
        load_toolbar()
        initFruits()
        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        val recycler_view=findViewById<RecyclerView>(R.id.recyclerview)
        recycler_view.layoutManager=layoutManager
        val adapter= RecyclerFruitAdapter(fruit_list)
        recycler_view.adapter=adapter

    }
    private fun initFruits(){

        fruit_list.add(Fruit("Apple",R.drawable.apple_pic))
        fruit_list.add(Fruit("Pear",R.drawable.pear_pic))
        fruit_list.add(Fruit("Grape",R.drawable.grape_pic))
        fruit_list.add(Fruit("Pineapple",R.drawable.pineapple_pic))
        fruit_list.add(Fruit("Strawberry",R.drawable.strawberry_pic))
        fruit_list.add(Fruit("Cherry",R.drawable.cherry_pic))
        fruit_list.add(Fruit("Mango",R.drawable.mango_pic))
        fruit_list.add(Fruit("Apricot",R.drawable.no_pic))
        fruit_list.add(Fruit("Blackberry",R.drawable.no_pic))
        fruit_list.add(Fruit("Blueberry",R.drawable.no_pic))
        fruit_list.add(Fruit("Crabapple",R.drawable.no_pic))
        fruit_list.add(Fruit("Grapefruit",R.drawable.no_pic))
        fruit_list.add(Fruit("Kiwifruit",R.drawable.no_pic))
        fruit_list.add(Fruit("Banana",R.drawable.no_pic))
        fruit_list.add(Fruit("Orange",R.drawable.no_pic))
        fruit_list.add(Fruit("Watermelon",R.drawable.no_pic))
        fruit_list.add(Fruit("Plum",R.drawable.no_pic))
    }
}