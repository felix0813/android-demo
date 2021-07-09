package com.example.viewtest


import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewtest.adapter.CardviewFruitAdapter
import com.example.viewtest.databinding.ActivityCardViewTestBinding
import com.example.viewtest.newclass.Fruit

class CardViewTest : BaseActivity() {
    private val fruit_list=ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityCardViewTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load_toolbar()
        initFruits()
        val layoutManager=GridLayoutManager(this,2)
        binding.cardViewRecycler.layoutManager=layoutManager
        binding.cardViewRecycler.adapter=CardviewFruitAdapter(this,fruit_list)

    }
    private fun initFruits(){

        fruit_list.add(Fruit("Apple",R.drawable.apple))

        fruit_list.add(Fruit("Banana",R.drawable.banana))

        fruit_list.add(Fruit("Orange",R.drawable.orange))

        fruit_list.add(Fruit("Pineapple",R.drawable.pineapple))

        fruit_list.add(Fruit("Pear",R.drawable.pear))

        fruit_list.add(Fruit("Grape",R.drawable.grape))

        fruit_list.add(Fruit("Strawberry",R.drawable.strawberry))

        fruit_list.add(Fruit("Cherry",R.drawable.cherry))

        fruit_list.add(Fruit("Mango",R.drawable.mango))

        fruit_list.add(Fruit("Watermelon",R.drawable.watermelon))

    }
}