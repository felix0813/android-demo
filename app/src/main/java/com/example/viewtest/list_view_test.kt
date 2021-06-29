package com.example.viewtest

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.viewtest.adapter.FruitAdapter
import com.example.viewtest.newclass.Fruit

class list_view_test : BaseActivity() {
    private val fruit_list= ArrayList<Fruit>()
    private val list= mutableListOf<String>("Apple","Apricot","Blackberry","Blueberry","Crabapple","Grapefruit","Kiwifruit","Banana","orange","Watermelon","Plum","Peach","Pear","Grape","Pineapple","Strawberry","Cherry","Mango","Orange")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_test)
        load_toolbar()
        initFruits()
        val adapter= FruitAdapter(this,R.layout.fruit_item,fruit_list)
        val list_view=findViewById<ListView>(R.id.list_view)
        list_view.adapter=adapter
        list_view.setOnItemClickListener { parent, view, position, id ->
            val fruit=fruit_list[position]
            Toast.makeText(this,fruit.name,Toast.LENGTH_SHORT).show()
        }
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