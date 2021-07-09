package com.example.viewtest


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog

class ImageTest : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_test)
        load_toolbar()
        val change_img: Button =findViewById(R.id.change_img)
        var num=1

        change_img.setOnClickListener {
            val progressBar=findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility= View.VISIBLE
            num++
            if(num==4){
                num=1
                AlertDialog.Builder(this).apply {
                    setTitle("提示")
                    setMessage("只有三张图片")
                    setPositiveButton("继续"){ _, _ ->}
                    show()
                }

            }
            val img: ImageView =findViewById(R.id.img)

            when(num){
                1->img.setImageResource(R.drawable.wei)
                2->img.setImageResource(R.drawable.zhuo)
                3->img.setImageResource(R.drawable.fei)
            }
            progressBar.visibility= View.GONE

        }
    }
}