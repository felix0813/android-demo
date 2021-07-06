package com.example.viewtest

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button

class multimedia_test : BaseActivity() {
    val mediaPlayer=MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia_test)
        load_toolbar()
        val manager=assets
        val fd=manager.openFd("music.mp3")

        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
        val play_audio=findViewById<Button>(R.id.play_audio)
        play_audio.setOnClickListener {
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }
        val pause_audio=findViewById<Button>(R.id.pause_audio)
        pause_audio.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }
        val stop_audio=findViewById<Button>(R.id.stop_audio)
        stop_audio.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.reset()
                mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
                mediaPlayer.prepare()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}