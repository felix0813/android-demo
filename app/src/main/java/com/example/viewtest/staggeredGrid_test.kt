package com.example.viewtest

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.viewtest.adapter.GridAdapter
import com.example.viewtest.adapter.recyclerFruitAdapter
import com.example.viewtest.newclass.Fruit

class staggeredGrid_test : BaseActivity() {
    val fruit_list=ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staggered_grid_test)
        initFruits()
        load_toolbar()
        val layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        val staggeredGrid=findViewById<RecyclerView>(R.id.staggeredGrid)
        staggeredGrid.layoutManager=layoutManager
        val adapter= GridAdapter(fruit_list)
        staggeredGrid.adapter=adapter
    }
    private fun initFruits(){

        fruit_list.add(Fruit(repeat_name("Apple"),R.drawable.apple_pic))
        fruit_list.add(Fruit(repeat_name("Pear"),R.drawable.pear_pic))
        fruit_list.add(Fruit(repeat_name("Grape"),R.drawable.grape_pic))
        fruit_list.add(Fruit(repeat_name("Pineapple"),R.drawable.pineapple_pic))
        fruit_list.add(Fruit(repeat_name("Strawberry"),R.drawable.strawberry_pic))
        fruit_list.add(Fruit(repeat_name("Cherry"),R.drawable.cherry_pic))
        fruit_list.add(Fruit(repeat_name("Mango"),R.drawable.mango_pic))
        fruit_list.add(Fruit(repeat_name("Apricot"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Blackberry"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Blueberry"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Crabapple"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Grapefruit"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Kiwifruit"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Banana"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Orange"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Watermelon"),R.drawable.no_pic))
        fruit_list.add(Fruit(repeat_name("Plum"),R.drawable.no_pic))
    }
    private fun repeat_name(name:String) :String{
        val result=StringBuilder()
        repeat((1..20).random()){
            result.append(name)
        }
        return result.toString()
    }
}