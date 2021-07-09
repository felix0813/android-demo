package com.example.viewtest

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import java.io.File

class CameraTest : BaseActivity() {
    lateinit var output:File
    lateinit var uri: Uri
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_test)
        load_toolbar()
        findViewById<ImageView>(R.id.photo)
        val camera=findViewById<Button>(R.id.camera_button)

        camera.setOnClickListener {
            output=File(externalCacheDir,"output_image.jpg")
            if(output.exists()){
                output.delete()
            }
            output.createNewFile()
            uri=if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this,"com.example.viewtest.fileprovider",output)
            }
            else{
                Uri.fromFile(output)
            }
            val intent= Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            startActivityForResult(intent,1)
        }
        val album=findViewById<Button>(R.id.album_button)
        album.setOnClickListener {
            val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type="image/*"
            startActivityForResult(intent,2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                if(resultCode== Activity.RESULT_OK){
                    val bitmap=BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
                    findViewById<ImageView>(R.id.photo).setImageBitmap(bitmap)
                }
            }
            2->{
                if(resultCode==Activity.RESULT_OK&&data!=null){
                    data.data?.let {uri->
                        val bitmap=contentResolver.openFileDescriptor(uri,"r")?.use {
                            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
                        }
                        findViewById<ImageView>(R.id.photo).setImageBitmap(bitmap)
                    }

                }

            }
        }
    }
}